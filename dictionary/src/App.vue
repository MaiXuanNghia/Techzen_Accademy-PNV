<template>
  <div class="container">
    <h1>Từ Điển</h1>
    <div class="search-bar">
      <input 
        type="text" 
        v-model="word" 
        placeholder="Nhập từ cần tra" 
      />
      <button @click="searchWord" id="bSearch">Tìm kiếm</button>
    </div>
    <div class="result" v-if="meaning">
      <p>{{ meaning }}</p>
    </div>
    <div class="result" v-if="definition">
      <p>{{ definition }}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      word: "",
      meaning: "",
      definition: "",
    };
  },
  methods: {
    searchWord() {
      if (!this.word.trim()) {
        this.meaning = "Vui lòng nhập một từ để tra cứu.";
        this.definition = "";
        return;
      }

      axios
        .get(`http://localhost:8080/dictionary/lookup?word=${this.word}`)
        .then((response) => {
          this.meaning = response.data.meaning || "Không tìm thấy ý nghĩa.";
          this.definition = response.data.definition || "Không tìm thấy định nghĩa.";
        })
        .catch((error) => {
          console.error(error);
          this.meaning = "Không tìm thấy từ này trong từ điển.";
          this.definition = "";
        });
    },
  },
};
</script>
