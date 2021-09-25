package uz.jurayev.spring_webflux_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jurayev.spring_webflux_ms.dto.response.ResponseModel;
import uz.jurayev.spring_webflux_ms.service.MathService;

import java.util.List;

@RestController
@RequestMapping("math")
public class SquareController {

    private final MathService service;

    @Autowired
    public SquareController(MathService service) {
        this.service = service;
    }

    @GetMapping("/square/{input}")
    public ResponseModel getSquare(@PathVariable int input){
        return service.findSquare(input);
    }

    @GetMapping("/table/{input}")
    public List<ResponseModel> getSquareTable(@PathVariable int input){
        return service.tableSquares(input);
    }
}
