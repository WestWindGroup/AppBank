
CREATE TABLE IF NOT EXISTS manager (
  idContract           INT(10)         UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  amountOfCredit       DECIMAL(20,5)   UNSIGNED  NOT NULL,
  sumOfIndebtedness    DECIMAL(20,5)   UNSIGNED  NOT NULL,
  durationOfContract   INT(5)          UNSIGNED  NOT NULL,
  typePayment          INT(5)          UNSIGNED  NOT NULL,
  interestRate         INT(5)          UNSIGNED  NOT NULL 
)
  ENGINE = InnoDB;
