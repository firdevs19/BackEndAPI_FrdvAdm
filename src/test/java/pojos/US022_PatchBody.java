package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class US022_PatchBody {

    /*
            {
"business_name":"Haiger AŞ",
"mobile":"9999999990",
"email":"fellerdilln@gmail.com"
}
     */
    private String business_name;
    private String mobile;
    private String email;

}
