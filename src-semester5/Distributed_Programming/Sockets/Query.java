
import java.io.Serializable;

public class Query implements Serializable
{
  private String name;

  public Query(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
} // Query
