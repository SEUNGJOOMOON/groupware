package RSR.DTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class RSRDataBean implements Serializable{
	private String reserve_listnum;
	public String getReserve_listnum() {
		return reserve_listnum;
	}
	public void setReserve_listnum(String reserve_listnum) {
		this.reserve_listnum = reserve_listnum;
	}
	private String reserve_date;
	private String reserve_time;
	private String reserve_room;
	private String reserve_name;
	private String reserve_num;
	private Timestamp rsr_date;
	
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
	public String getReserve_time() {
		return reserve_time;
	}
	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}
	public String getReserve_room() {
		return reserve_room;
	}
	public void setReserve_room(String reserve_room) {
		this.reserve_room = reserve_room;
	}
	public Timestamp getRsr_date() {
		return rsr_date;
	}
	public void setRsr_date(Timestamp rsr_date) {
		this.rsr_date = rsr_date;
	}
	public String getReserve_name() {
		return reserve_name;
	}
	public void setReserve_name(String reserve_name) {
		this.reserve_name = reserve_name;
	}
	public String getReserve_num() {
		return reserve_num;
	}
	public void setReserve_num(String reserve_num) {
		this.reserve_num = reserve_num;
	}

}
