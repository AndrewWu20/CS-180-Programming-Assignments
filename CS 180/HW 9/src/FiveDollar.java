public class FiveDollar extends DollarBill{
    public FiveDollar() {
        super(5, "Abraham Lincoln");
    }

    @Override
    /* Override acceptedVending method in coin class to
     * return true because vending machines accept one dollar bills
     */
    public boolean acceptedVending() {
        return true;
    }
}
