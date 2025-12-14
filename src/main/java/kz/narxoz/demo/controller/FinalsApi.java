package kz.narxoz.demo.controller;


import kz.narxoz.demo.dto.FinalsDto;
import kz.narxoz.demo.service.FinalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/finals")
public class FinalsApi {
    private final FinalsService finalsService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(finalsService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(finalsService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody FinalsDto finalsDto){
        finalsService.addFinals(finalsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody FinalsDto finalsDto){
        finalsService.updateFinals(id, finalsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        finalsService.deleteFinals(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
