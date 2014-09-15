// BookTitleService.java

package bookservice;

import java.io.*;
import java.util.*;

public class BookImpl implements BookIF {

   // Map contains ISBN/Book-Title pairs
   Map books;

   public BookImpl()
   {
      books = new HashMap();

      // store ISBN/Book-Title pairs in HashMap
      books.put("0130895601", 
         "Advanced Java 2 Platform How to Program");
      books.put("0130895717", 
         "C++ How to Program, Third edition");
      books.put("0130293636", 
         "Visual Basic .NET How to Program");
      books.put("0130284173", "XML How to Program");
      books.put("0130923613", "Python How to Program");
   }

   // service to obtain book title associated with ISBN number
   public String getBookTitle(String ISBN)
   {
      return (String)books.get(ISBN);
   }
}

