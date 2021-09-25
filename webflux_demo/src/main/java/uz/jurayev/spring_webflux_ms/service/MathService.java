package uz.jurayev.spring_webflux_ms.service;

import org.springframework.stereotype.Service;
import uz.jurayev.spring_webflux_ms.dto.response.ResponseModel;
import uz.jurayev.spring_webflux_ms.utils.SleepUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {

    public ResponseModel findSquare(int input){
        return new ResponseModel(input * input);
    }

    public List<ResponseModel> tableSquares(int input){
        return IntStream.range(1, 10)
                .peek(i -> SleepUtil.sleep(1))
                .peek(System.out::println)
                .mapToObj(e -> new ResponseModel(e * input))
                .collect(Collectors.toList());
    }
}
