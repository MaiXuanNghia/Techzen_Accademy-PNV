package vn.techzen.academy_pnv_25;

public class Dictionary {
    private String word;
    private String definition;
    private String meaning;
    public Dictionary(String word, String definition, String meaning) {
        this.word = word;
        this.meaning = meaning;
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

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
