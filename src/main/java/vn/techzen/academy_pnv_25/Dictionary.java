package vn.techzen.academy_pnv_25;

public class Dictionary {
    private String word;
    private String definition;
    public Dictionary(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getDefinition() {
        return definition;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
