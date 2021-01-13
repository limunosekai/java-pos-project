package kg.fx.lim.model;

public class Product {
	// 멤버필드
	private int code;
	private String category;
	private String name;
	private int quantity;
	private int price;
	private int salePrice;
	private int discountRate;
	private int discount;
	
	// 생성자
	public Product() {
	}
	public Product(int code, String category, String name, int quantity, int price, int salePrice, int discountRate,
			int discount) {
		super();
		this.code = code;
		this.category = category;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.salePrice = salePrice;
		this.discountRate = discountRate;
		this.discount = discount;
	}
	
	// 메소드
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
}
