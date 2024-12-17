<template>
  <div id="container">
    <!-- Search Form -->
    <div id="search-container">
      <h2>Tìm kiếm nhân viên</h2>
      <form @submit.prevent="searchEmployees">
        <div class="form-row">
          <div>
            <label for="name">Tên (Tìm kiếm gần đúng):</label>
            <input id="name" v-model="searchCriteria.name" type="text" />
          </div>
          <div>
            <label for="dob-from">Ngày sinh từ:</label>
            <input id="dob-from" v-model="searchCriteria.dobFrom" type="date" />
          </div>
          <div>
            <label for="dob-to">Ngày sinh đến:</label>
            <input id="dob-to" v-model="searchCriteria.dobTo" type="date" />
          </div>
        </div>
        <div class="form-row">
          <div>
            <label for="gender">Giới tính:</label>
            <select id="gender" v-model="searchCriteria.gender">
              <option value="">Tất cả</option>
              <option value="MALE">Nam</option>
              <option value="FEMALE">Nữ</option>
              <option value="OTHER">Khác</option>
            </select>
          </div>
          <div>
            <label for="salaryRange">Mức lương:</label>
            <select id="salaryRange" v-model="searchCriteria.salaryRange">
              <option value="">Tất cả</option>


              <option value="1-5">Dưới 5 triệu</option>
              <option value="5-10">Từ 5 - 10 triệu</option>
              <option value="10-20">Từ 10 - 20 triệu</option>
              <option value="gt20">Trên 20 triệu</option>
            </select>
          </div>
          <div>
            <label for="phone">Số điện thoại (Tìm kiếm gần đúng):</label>
            <input id="phone" v-model="searchCriteria.phone" type="text" />
          </div>
        </div>

        <label>Bộ phận:</label>
      <select v-model="searchCriteria.departmentId" :disabled="isViewMode" id="department">
        <option value="">Tất cả</option>
        <option v-for="department in departments" :key="department.id" :value="department.id">
          {{ department.name }}
        </option>
      </select><br />

        <div class="form-actions">
          <button type="button" class="btn-reset" @click="resetSearch">Đặt lại</button>
          <button @click="searchEmployees()" class="btn-search">Tìm kiếm</button>
        </div>
      </form>
    </div>

    <!-- Employee Table -->
    <h2>Danh sách nhân viên</h2>
    <table>
      <thead>
        <tr>
          <th>STT</th>
          <th>Tên</th>
          <th>Ngày sinh</th>
          <th>Giới tính</th>
          <th>Lương</th>
          <th>Số điện thoại</th>
          <th>Bộ phận</th>
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
          <td>{{ employee.phone }}</td>
          <td>{{ employee.department.name }}</td>
          <td id="action">
            <button class="btn-update" @click="showForm(employee, 'edit')">Cập nhật</button>
            <button class="btn-delete" @click="deleteEmployee(employee)">Xóa</button>
            <button class="btn-details" @click="showForm(employee, 'details')">Chi tiết</button>
          </td>
        </tr>
      </tbody>
    </table>
    <h3 v-if="noRecordsFound" style="color: red; text-align: center; width: 100vw;">Không có bản ghi nào được tìm thấy</h3>

    <button class="btn-add" @click="showForm(null, 'add')">+ Thêm Mới</button>
  </div>

  <!-- Overlay and Form -->
  <div id="overlay" v-if="isFormVisible">
    <form v-if="isFormVisible" @submit.prevent="handleFormSubmit" id="fAdd">
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

      <label>Bộ phận:</label>
      <select v-model="form.departmentId" :disabled="isViewMode" required>
        <option value="" disabled>Chọn bộ phận</option>
        <option v-for="department in departments" :key="department.id" :value="department.id">
          {{ department.name }}
        </option>
      </select><br />

      <button v-if="!isViewMode" type="submit" class="btn-save">{{ isEditing ? 'Cập nhật' : 'Thêm mới' }}</button>
      <button type="button" class="btn-close" @click="hideForm">Đóng</button>
    </form>
</div>

<div class="pagination">
  <button @click="previousPage" :disabled="currentPage === 0">Trang trước</button>
  <span>Trang {{ currentPage + 1 }} / {{ totalPages }}</span>
  <button @click="nextPage" :disabled="currentPage >= totalPages - 1">Trang sau</button>
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

export function formatToVND(salary) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(salary).toString();
}


export default {
    data() {
    return {
      noRecordsFound: false,
      formMode: "",
      isFormVisible: false,
      employees: [],
      departments: [],
      currentPage: 0, // Trang hiện tại (index từ 0)
      totalPages: 0,  // Tổng số trang
      pageSize: 5,    // Số bản ghi trên mỗi trang
      searchCriteria: {
        name: "",
        dobFrom: "",
        dobTo: "",
        gender: "",
        salaryRange: "",
        phone: "",
        departmentId: "",
      },
    };
  },
  methods: {
    fetchEmployees(page = 0) {
      this.currentPage = page; // Cập nhật trang hiện tại
      axios
        .get("http://localhost:8080/employees", {
          params: {
            ...this.searchCriteria,
            page: this.currentPage,
            size: this.pageSize,
          },
        })
        .then((response) => {
          const { content, page } = response.data.data;
          console.log(content);
          this.employees = content;
          this.totalPages = page.totalPages;
          this.noRecordsFound = this.employees.length === 0;
          if (this.employees.length <= 0) {
            this.noRecordsFound = true;
          }
        })
        .catch((error) => {
          console.error("Lỗi khi tải nhân viên:", error);
        });
    },
    nextPage() {
      if (this.currentPage < this.totalPages - 1) {
        this.fetchEmployees(this.currentPage + 1);
      }
    },
    previousPage() {
      if (this.currentPage > 0) {
        this.fetchEmployees(this.currentPage - 1);
      }
    },
    resetSearch() {
      this.searchCriteria = {
        name: "",
        dobFrom: "",
        dobTo: "",
        gender: "",
        salaryRange: "",
        phone: "",
        departmentId: "",
      };
      this.fetchEmployees();
    },

    async fetchDepartments() {
      try {
        const response = await axios
          .get("http://localhost:8080/departments");
        this.departments = response.data.data;
      } catch (error) {
        console.error(error);
      }
    },
    searchEmployees() {
      axios
        .get("http://localhost:8080/employees/search", { params: this.searchCriteria })
        .then((response) => {
          this.employees = response.data.data;
          if (this.employees.length <= 0) {
            this.noRecordsFound = true;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    resetSearch() {
      this.searchCriteria = {
        name: "",
        dobFrom: "",
        dobTo: "",
        gender: "",
        salaryRange: "",
        phone: "",
        departmentId: "",
      };
      this.fetchEmployees();
    },
    handleFormSubmit() {
      if (this.isEditing) {
        console.log({
            id: this.form.id,
            name: this.form.name,
            dob: this.form.dob,
            gender: this.form.gender,
            salary: this.form.salary,
            phone: this.form.phone,
            department: {id: this.form.departmentId, name: this.departments[this.form.departmentId - 1].name},
          });
        axios
          .put("http://localhost:8080/employees", {
            id: this.form.id,
            name: this.form.name,
            dob: this.form.dob,
            gender: this.form.gender,
            salary: this.form.salary,
            phone: this.form.phone,
            department: {id: this.form.departmentId, name: this.departments[this.form.departmentId - 1].name},
          })
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
            name: this.form.name,
            dob: this.form.dob,
            gender: this.form.gender,
            salary: this.form.salary,
            phone: this.form.phone,
            department: {id: this.form.departmentId, name: this.departments[this.form.departmentId - 1].name},
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
    showForm(employee = null, mode = "add") {
      console.log(employee);
      this.formMode = mode;
      this.form = employee
        ? { ...employee, departmentId: employee.department.id }
        : {
            id: "",
            name: "",
            dob: "",
            gender: "",
            salary: "",
            phone: "",
            departmentId: "",
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
  computed: {
    isViewMode() {
      return this.formMode === "details";
    },
    isEditing() {
      return this.formMode === "edit";
    },
    formTitle() {
      if (this.formMode === "details") return "Thông tin chi tiết của nhân viên";
      return this.isEditing ? "Sửa Nhân Viên" : "Thêm Nhân Viên";
    },
  },
  mounted() {
    Promise.all([this.fetchDepartments(), this.fetchEmployees()])
      .then(() => {
        console.log("Dữ liệu đã tải xong");
      })
      .catch((error) => {
        console.error("Lỗi tải dữ liệu", error);
      });
  },
};
</script>
