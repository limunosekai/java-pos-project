package kg.fx.lim.model;

public class OrderProductList {
	// 멤버필드
	private int orderNumber;
	private String orderDate;
	private String orderProductName;
	private String orderProductCategory;
	private int orderProductQuantity;
	private int orderProductPrice;
	private int orderProductSalePrice;
	private int orderProductDiscountRate;
	private int orderProductDiscount;
	
	// 생성자
	public OrderProductList() {
		
	}
	public OrderProductList(int orderNumber, String orderDate, String orderProductName, String orderProductCategory,
			int orderProductQuantity, int orderProductPrice, int orderProductSalePrice, int orderProductDiscountRate,
			int orderProductDiscount) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderProductName = orderProductName;
		this.orderProductCategory = orderProductCategory;
		this.orderProductQuantity = orderProductQuantity;
		this.orderProductPrice = orderProductPrice;
		this.orderProductSalePrice = orderProductSalePrice;
		this.orderProductDiscountRate = orderProductDiscountRate;
		this.orderProductDiscount = orderProductDiscount;
	}
	
	// 메소드
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderProductName() {
		return orderProductName;
	}
	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
	}
	public String getOrderProductCategory() {
		return orderProductCategory;
	}
	public void setOrderProductCategory(String orderProductCategory) {
		this.orderProductCategory = orderProductCategory;
	}
	public int getOrderProductQuantity() {
		return orderProductQuantity;
	}
	public void setOrderProductQuantity(int orderProductQuantity) {
		this.orderProductQuantity = orderProductQuantity;
	}
	public int getOrderProductPrice() {
		return orderProductPrice;
	}
	public void setOrderProductPrice(int orderProductPrice) {
		this.orderProductPrice = orderProductPrice;
	}
	public int getOrderProductSalePrice() {
		return orderProductSalePrice;
	}
	public void setOrderProductSalePrice(int orderProductSalePrice) {
		this.orderProductSalePrice = orderProductSalePrice;
	}
	public int getOrderProductDiscountRate() {
		return orderProductDiscountRate;
	}
	public void setOrderProductDiscountRate(int orderProductDiscountRate) {
		this.orderProductDiscountRate = orderProductDiscountRate;
	}
	public int getOrderProductDiscount() {
		return orderProductDiscount;
	}
	public void setOrderProductDiscount(int orderProductDiscount) {
		this.orderProductDiscount = orderProductDiscount;
	}
}
