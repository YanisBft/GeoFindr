package com.yanisbft.geofindr;

public class Currency {
    private String name;
    private String code;
    private String symbol;

    public Currency(String name, String code, String symbol) {
        this.name = name;
        this.code = code;
        this.symbol = symbol;

        DataProvider.ALL_CURRENCY_SYMBOLS.add(this.symbol);
    }

    /**
     * Returns the name of this currency.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the ISO 4217 code of this currency.
     * @see <a href="https://en.wikipedia.org/wiki/ISO_4217">ISO 4217</a>
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the symbol of this currency, usually with latin characters.
     */
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return this.getName() + " (" + this.getSymbol() + ")";
    }
}
