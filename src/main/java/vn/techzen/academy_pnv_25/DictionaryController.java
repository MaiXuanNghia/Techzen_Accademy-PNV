package vn.techzen.academy_pnv_25;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    private List<Dictionary> dictionarys = Arrays.asList(
            new Dictionary("Banana", "A long curved fruit that grows in clusters and has soft pulpy flesh and yellow skin when ripe.", "Qủa chuối"),
            new Dictionary("Apple", "A round fruit with red, yellow, or green skin and firm white flesh.", "Qủa táo"),
            new Dictionary("Orange", "A citrus fruit with a tough bright reddish-yellow rind and juicy, tangy flesh.", "Qủa cam"),
            new Dictionary("Grapes", "A small round fruit that grows in bunches on vines and can be green, red, or purple.", "Qủa nho"),
            new Dictionary("Mango", "A tropical stone fruit with a smooth skin, sweet flesh, and a large pit.", "Qủa xoài"),
            new Dictionary("Pineapple", "A large tropical fruit with a tough spiky skin and sweet yellow flesh inside.", "Qủa dứa")
    );

    @GetMapping("/lookup")
    public ResponseEntity<String> lookup(@RequestParam (defaultValue = " ") String word) {
        for (Dictionary dictionary : dictionarys) {
            if (dictionary.getWord().equals(word)) {
                return ResponseEntity.ok(dictionary.getDefinition() + "\n" + dictionary.getMeaning());
            }
        }

        return ResponseEntity.status(404).body("Word not found in the dictionary.");
    }
}
