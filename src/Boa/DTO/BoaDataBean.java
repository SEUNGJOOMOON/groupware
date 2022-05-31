package Boa.DTO;
import java.sql.Timestamp;
public class BoaDataBean {
	
	
	private String board_num;
	private String board_writerid;
	private String board_writer;
	private String board_title;
	private String board_content;
	private int board_hit;
	private Timestamp board_date;


	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	public String getBoard_writerid() {
		return board_writerid;
	}
	public void setBoard_writerid(String board_writerid) {
		this.board_writerid = board_writerid;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_hit() {
		return board_hit;
	}
	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}
	public Timestamp getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Timestamp board_date) {
		this.board_date = board_date;
	}
	

}
