public class OneDollar extends DollarBill{
    public OneDollar() {
        super(1, "George Washington");
    }

    @Override
    /* Override acceptedVending method in coin class to
     * return true because vending machines accept one dollar bills
     */
    public boolean acceptedVending() {
        return true;
    }
}
