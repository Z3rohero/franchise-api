package com.franchise.infrastructure.utils.constants;

public final class MessageConstants {

    public static final String FRANCHISE_CREATED = "Franchise created successfully.";
    public static final String FRANCHISE_RETRIEVED = "Franchise retrieved successfully.";
    public static final String FRANCHISE_LIST_RETRIEVED = "Franchises retrieved successfully.";
    public static final String FRANCHISE_NAME_UPDATED = "Franchise name updated successfully.";

    public static final String BRANCH_CREATED = "Branch created successfully.";
    public static final String BRANCH_NAME_UPDATED = "Branch name updated successfully.";

    public static final String PRODUCT_CREATED = "Product created successfully.";
    public static final String PRODUCT_DELETED = "Product deleted successfully.";
    public static final String PRODUCT_STOCK_UPDATED = "Product stock updated successfully.";
    public static final String PRODUCT_NAME_UPDATED = "Product name updated successfully.";

    public static final String TOP_STOCK_PRODUCTS_RETRIEVED = "Top stock products per branch retrieved successfully.";

    public static final String NAME_REQUIRED = "Please provide a name.";
    public static final String NAME_MAX_LENGTH = "Name can have at most 180 characters.";
    public static final String STOCK_REQUIRED = "Please provide a stock value.";
    public static final String STOCK_NOT_NEGATIVE = "Stock cannot be negative.";

    public static final String INVALID_REQUEST = "Please check the submitted data and try again.";
    public static final String UNEXPECTED_ERROR = "Something went wrong. Please try again later.";

    private MessageConstants() {
    }
}
