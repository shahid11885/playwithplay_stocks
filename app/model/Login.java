package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@NoArgsConstructor(force = true)
@Getter
@Setter
@ToString
@Log

public class Login {
    String consumerKey;
    String consumerSecret;
}
