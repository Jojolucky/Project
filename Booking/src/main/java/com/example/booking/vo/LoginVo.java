package com.example.booking.vo;

//import com.example.booking.validator.IsMobile;
        import com.example.booking.validator.IsMobile;
        import jakarta.validation.constraints.NotNull;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.hibernate.validator.constraints.Length;
//        import org.hibernate.validator.constraints.Length;
//        import javax.validation.constraints.NotNull;

/**
 * 登录入参 *
 * @author zhoubin
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min = 32)
    private String password;

}