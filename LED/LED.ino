#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

#define  LED           13

AndroidAccessory acc("Google, Inc.",
		     "DemoKit",
		     "DemoKit Arduino Board",
		     "1.0",
		     "http://www.android.com",
		     "0000000012345678");

void setup();
void loop();

void setup()
{
  Serial.begin(9600);
  Serial.println("Start");

  pinMode(LED, OUTPUT);
  digitalWrite(LED, LOW);

  acc.powerOn();
}

void loop()
{
  byte msg[3];

  if (acc.isConnected()) {
                
    int len = acc.read(msg, sizeof(msg), 1);

    if (len > 0) {

      // msg[0] : Command
      // msg[1] : Target
      // msg[2] : Value
        
      Serial.print("len: ");
      Serial.println(len);
      Serial.print("msg[0]: ");
      Serial.println(msg[0]);
      Serial.print("msg[1]: ");
      Serial.println(msg[1]);
      Serial.print("msg[2]: ");
      Serial.println(msg[2]);

      if (msg[0] == 0x03) {
        if (msg[2] == 0x00) {
          digitalWrite(LED, LOW);
        } else {
          digitalWrite(LED, HIGH);
        }
      }
    }
  }
  delay(10);
}
