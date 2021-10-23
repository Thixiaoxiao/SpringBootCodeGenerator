<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.entity;</#if>

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseForLayUIEntity<T> {
    private static final int SUCCESS = 0;
    private static final int FAIL = 1;

    private Integer code;
    private Integer count;
    private String msg;
    private T data;

    public static <T> ResponseForLayUIEntity success(T data) {
        return ResponseForLayUIEntity.builder()
            .data(data)
            .msg("success")
            .code(SUCCESS)
            .build();
    }

    public static <T> ResponseForLayUIEntity success(T data, Integer count) {
        return ResponseForLayUIEntity.builder()
                .data(data)
                .msg("success")
                .code(SUCCESS)
                .count(count)
                .build();
    }

    public static ResponseForLayUIEntity fail(String msg) {
        return ResponseForLayUIEntity.builder()
            .msg(msg)
            .code(FAIL)
            .build();
    }
}