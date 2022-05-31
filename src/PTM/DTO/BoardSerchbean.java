package PTM.DTO;

public class BoardSerchbean {
	
	private int Serchpage;
	public String Keyword;
	private int pageNum;

	public String getKeyword() {
		return Keyword;
		
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
		this.Keyword = Keyword;
	}

	public int getSerchpage() {
		return Serchpage;
	}

	public void setSerchpage(int serchpage) {
		Serchpage = serchpage;
		this.Serchpage=serchpage;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}


