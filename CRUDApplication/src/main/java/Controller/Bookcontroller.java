package Controller;

import model.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.BookRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class Bookcontroller {
    @Autowired
    BookRepo bookrepo;
    @GetMapping("/books")
    public ResponseEntity<List<book>> getAllbooks(){
        try{
            List<book> books=new ArrayList<>();
            bookrepo.findAll().forEach(books::add);
            if (books.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(books ,HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getbookbyID(@PathVariable Long id){
        Optional<book> optionalbook= bookrepo.findById(id);
        if (optionalbook.isPresent()){
            return new ResponseEntity<>(optionalbook.get(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @PostMapping("/books")
    public ResponseEntity<book> addbook(@RequestBody book book){
        book bookobj=bookrepo.save(book);
        return new ResponseEntity<>(bookobj,HttpStatus.OK);

    }
    @PostMapping("/books/{id}")
    public ResponseEntity<book> updatebookbyID(@PathVariable Long id,@RequestBody book newbookData){
        Optional<book> book_old_data=bookrepo.findById(id);
        if (book_old_data.isPresent()){
            book update_book=book_old_data.get();
            update_book.setAuthor(newbookData.getAuthor());
            update_book.setTitle(newbookData.getTitle());
            book bookobj=bookrepo.save(update_book);
            return new ResponseEntity<>(bookobj,HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/books/{ID}")
    public ResponseEntity<book> deletebookbyID(@PathVariable Long id){
        bookrepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);


    }

}
