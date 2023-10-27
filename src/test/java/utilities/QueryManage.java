package utilities;

public class QueryManage {

    private String query03 = "SELECT * FROM u168183796_qaagiles.delivery_man;UPDATE delivery_man SET delivery_charge = 333.33, WHERE id = 13";


    private String queryUS10_CIGDEM = "insert into u168183796_qaagiles.deliverycategories (id,created_at,position,status,title," +
            "updated_at)\n" + "values (20,current_timestamp(),10,3,'query2',current_timestamp() ),\n" +
            "       (21,current_timestamp(),10,3,'query2',current_timestamp() ),\n" +
            "       (22,current_timestamp(),10,3,'query2',current_timestamp() );";
    private  String queryUS19_CIGDEM = "select business_name,address from u168183796_qaagiles.merchants where business_name " + "like '%m%' and business_name like '%n%';";

    public String getQuery03()
    {
        return query03;
    }

    public String getQueryUS10_CIGDEM() {
        return queryUS10_CIGDEM;
    }

    public String getQueryUS19_CIGDEM() {
        return queryUS19_CIGDEM;
    }




    private  String queryUS05_mustafa= "select amount from u168183796_qaagiles.bank_transactions where created_at <= '2023-10-01 20:44:08' and created_at >= '2023-08-23 11:30:34' order by amount;";
    private String queryUS23_alter_mustafa ="alter table parcels add column ismi_ali_olanlar int; ";
    private String queryUS23_update1_mustafa="UPDATE parcels SET ismi_ali_olanlar=1 WHERE customer_name like '%ali%';";
    private String queryUS23_update0_mustafa="UPDATE parcels SET ismi_ali_olanlar=0 WHERE customer_name not like '%ali%';";
    private String queryUS23_delete_mustafa="ALTER TABLE parcels DROP COLUMN ismi_ali_olanlar;";
    private String queryUS14_mustafa ="select name, address from hubs where name like 'S%';";



    public String getQueryUS14_mustafa() {
        return queryUS14_mustafa;
    }
    public String getQueryUS05_mustafa() {
        return queryUS05_mustafa;
    }
    public String getQueryUS23_alter_mustafa() {return queryUS23_alter_mustafa;}
    public String getQueryUS23_update1_mustafa() {return queryUS23_update1_mustafa;}
    public String getQueryUS23_update0_mustafa() {return queryUS23_update0_mustafa;}
    public String getQueryUS23_delete_mustafa() {return queryUS23_delete_mustafa;}

}



