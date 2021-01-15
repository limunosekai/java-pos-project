package kg.fx.lim.model;

public class OrderDetail {
	// 멤버필드
	private int orderDetailNumber;
	private int orderProductQuantity;
	private int orderNumber;
	private int productCode;
	
	// 생성자
	public OrderDetail() {
		
	}
	public OrderDetail(int orderDetailNumber, int orderProductQuantity, int orderNumber,int productCode) {
		super();
		this.orderDetailNumber = orderDetailNumber;
		this.orderProductQuantity = orderProductQuantity;
		this.orderNumber = orderNumber;
		this.productCode = productCode;
	}
	
	// 메소드
	public int getOrderDetailNumber() {
		return orderDetailNumber;
	}
	public void setOrderDetailNumber(int orderDetailNumber) {
		this.orderDetailNumber = orderDetailNumber;
	}
	public int getOrderProductQuantity() {
		return orderProductQuantity;
	}
	public void setOrderProductQuantity(int orderProductQuantity) {
		this.orderProductQuantity = orderProductQuantity;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
}
