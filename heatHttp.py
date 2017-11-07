from flask import Flask
import RPi.GPIO as GPIO
from pi_sht1x import SHT1x
from flask import jsonify

DATA_PIN = 24
SCK_PIN = 23

app = Flask(__name__)

@app.route('/')
def hello_world():
    with SHT1x(DATA_PIN, SCK_PIN, gpio_mode=GPIO.BCM) as sensor:
            sensor.read_humidity()
    return jsonify(temp=sensor.temperature_celsius, humidity=sensor.humidity)

if __name__ == '__main__':
    app.run(host='0.0.0.0')