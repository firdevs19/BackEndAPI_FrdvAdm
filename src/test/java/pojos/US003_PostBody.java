package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class US003_PostBody {

        //(name,phone,address,current balance,status)
        private String name;
        private String phone;
        private String address;
        private double current_balance;
        private int status;



}
