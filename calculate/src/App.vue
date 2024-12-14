<template>
  <div class="container">
    <h1>Máy Tính Đơn Giản</h1>
    <div>
      <label for="num1">Số thứ nhất:</label>
      <input
        v-model="num1"
        type="number"
        id="num1"
        placeholder="Nhập số thứ nhất"
      />

      <label for="num2">Số thứ hai:</label>
      <input
        v-model="num2"
        type="number"
        id="num2"
        placeholder="Nhập số thứ hai"
      />

      <label for="operation">Phép toán:</label>
      <select v-model="operation" id="operation">
        <option value="add">Cộng</option>
        <option value="subtract">Trừ</option>
        <option value="multiply">Nhân</option>
        <option value="divide">Chia</option>
      </select>

      <button @click="calculate">Tính toán</button>

      <div class="result">
        <p>Kết quả: {{ result }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SimpleCalculator",
  data() {
    return {
      num1: 0,
      num2: 0,
      operation: "add",
      result: "",
    };
  },
  methods: {
    async calculate() {
      try {
        const response = await axios.get("http://localhost:8080/calculator", {
          params: {
            firstNumberStr: this.num1,
            secondNumberStr: this.num2,
            operation: this.operation,
          },
        });
        this.result = response.data;
      } catch (error) {
        this.result = error.response.data;
      }
    },
  },
};
</script>
