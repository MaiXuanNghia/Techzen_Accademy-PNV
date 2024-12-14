<template>
  <div class="container">
    <div class="title">{{ message }}</div>
    <div>
      <input
        type="text"
        v-model="name"
        placeholder="Nhập name thay đổi param phía back-end"
      />
      <button @click="getInputValue">Submit</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      message: "Loading...",
      name: "",
    };
  },
  methods: {
    getInputValue() {
      axios
        .get(`http://localhost:8080/greeting?name=${this.name}`)
        .then((response) => {
          this.message = response.data;
        })
        .catch((error) => {
          console.error(error);
          this.message = "Failed to load data.";
        });
    },
  },
  mounted() {
    axios
      .get(`http://localhost:8080/greeting`)
      .then((response) => {
        this.message = response.data;
      })
      .catch((error) => {
        console.error(error);
        this.message = "Failed to load data.";
      });
  },
};
</script>
