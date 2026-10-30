import java.util.Objects;

public class Card {
    private String rank, suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card c = (Card) obj;
        return Objects.equals(this.rank, c.rank) && Objects.equals(this.suit, c.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
