package utils;

// DB에서 검색할 행의 정보를 처리할 class
// 정보를 출력할 기준을 잡기 위해
// 요청 page정보를 저장
public class Criteria {
	// 요청이 들어온 현재 페이지 번호
	private int page;
	// 한 페이지에 보여 줄 게시물 개수
	private int perPageNum;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int page, int perPageNum) {
		setPage(page);
		setPerPageNum(perPageNum);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	// 시작 row 인덱스 번호
	// limit 시작인덱스 번호, 개수;
	// limit getStartRow(), perPageNum;
	public int getStartRow() {
		return (this.page-1)*perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}










