<template>
  <div id="container">
    <table>
      <thead>
        <tr>
          <th>STT</th>
          <th>Tên</th>
          <th>Ngày sinh</th>
          <th>Giới tính</th>
          <th>Lương</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(employee, index) in employees" :key="employee.id">
          <td>{{ index + 1 }}</td>
          <td>{{ employee.name }}</td>
          <td>{{ employee.dob }}</td>
          <td>{{ employee.gender }}</td>
          <td>{{ employee.salary }}</td>
          <td>
            <button @click="showForm(employee, 'details')">Xem Chi tiết</button>
            <button @click="showForm(employee, 'edit')">Sửa</button>
            <button @click="deleteEmployee(employee)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>

    <button @click="showForm(null, 'add')">Thêm Nhân Viên</button>

    <div id="overlay" @click="hideForm" v-if="isFormVisible"></div>

    <form v-if="isFormVisible" @submit.prevent="handleFormSubmit">
      <h2 id="title">{{ formTitle }}</h2>

      <label>Tên:</label>
      <input v-model="form.name" :disabled="isViewMode" required /><br />

      <label>Ngày sinh:</label>
      <input type="date" v-model="form.dob" :disabled="isViewMode" required /><br />

      <label>Giới tính:</label>
      <select v-model="form.gender" :disabled="isViewMode" required>
        <option value="MALE">Nam</option>
        <option value="FEMALE">Nữ</option>
        <option value="OTHER">Khác</option>
      </select><br />

      <label>Lương:</label>
      <input type="number" v-model="form.salary" :disabled="isViewMode" required /><br />

      <label>Số điện thoại:</label>
      <input v-model="form.phone" :disabled="isViewMode" required /><br />

      <button v-if="!isViewMode" type="submit">{{ isEditing ? 'Cập nhật' : 'Thêm mới' }}</button>
      <button type="button" @click="hideForm">Đóng</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export function generateUUID() {
  return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (c) {
    const r = (Math.random() * 16) | 0,
      v = c === "x" ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
}

export default {
  data() {
    return {
      employees: [],
      form: {
        id: "",
        name: "",
        dob: "",
        gender: "",
        salary: "",
        phone: "",
      },
      formMode: "",
      isFormVisible: false,
    };
  },
  computed: {
    isViewMode() {
      return this.formMode === 'details';
    },
    isEditing() {
      return this.formMode === 'edit';
    },
    formTitle() {
      if (this.formMode === 'details') return 'Thông tin chi tiết của nhân viên';
      return this.isEditing ? 'Sửa Nhân Viên' : 'Thêm Nhân Viên';
    },
  },
  mounted() {
    this.fetchEmployees();
  },
  methods: {
    fetchEmployees() {
      axios
        .get("http://localhost:8080/employees")
        .then((response) => {
          this.employees = response.data.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleFormSubmit() {
      if (this.isEditing) {
        axios
          .put("http://localhost:8080/employees", this.form)
          .then(() => {
            this.fetchEmployees();
            this.hideForm();
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        axios
          .post("http://localhost:8080/employees", {
            id: generateUUID(),
            name: this.form.name,
            dob: this.form.dob,
            gender: this.form.gender,
            salary: this.form.salary,
            phone: this.form.phone,
          })
          .then(() => {
            this.fetchEmployees();
            this.hideForm();
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
    showForm(employee = null, mode = 'add') {
      this.formMode = mode;
      this.form = employee
        ? { ...employee }
        : {
            id: "",
            name: "",
            dob: "",
            gender: "",
            salary: "",
            phone: "",
          };
      this.isFormVisible = true;
    },
    hideForm() {
      this.isFormVisible = false;
    },
    deleteEmployee(employee) {
      axios
        .delete("http://localhost:8080/employees", { data: employee })
        .then(() => {
          this.fetchEmployees();
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
