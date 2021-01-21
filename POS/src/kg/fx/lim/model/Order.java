package kg.fx.lim.model;

/**
---------------------------------------------------------------------------
* Nintendo POS 1.0
* Order
* @author 임성현
---------------------------------------------------------------------------
*/

public class Order {
	// 멤버필드
	private int orderNumber;
	private String orderDate;
	private int totalAmount;
	private int userCode;
	
	// 생성자
	public Order() {
		
	}

	public Order(int orderNumber, String orderDate, int totalAmount, int userCode) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.userCode = userCode;
	}
	// 메소드
	public int getOrderNumber() {
		return orderNumber;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int productCode) {
		this.userCode = productCode;
	}
}
