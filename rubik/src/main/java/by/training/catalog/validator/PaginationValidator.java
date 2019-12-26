package by.training.catalog.validator;

public class PaginationValidator {
    public static boolean isValidPage(int page) {
        return page > 0;
    }

    public static boolean isValidLimit(int limit) {
        return limit >= 0;
    }
}
