package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class US019_PostBody {
//name,business_name,mobile,email,password,address,hub_id,status
    private String name;
    private String business_name;
    private String mobile;
    private String email;
    private String password;
    private String address;
    private String hub_id;
    private String status;


}
