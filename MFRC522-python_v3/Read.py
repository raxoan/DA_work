#!/usr/bin/env python
# -*- coding: utf8 -*-
#
#    Copyright 2014,2018 Mario Gomez <mario.gomez@teubi.co>
#
#    This file is part of MFRC522-Python
#    MFRC522-Python is a simple Python implementation for
#    the MFRC522 NFC Card Reader for the Raspberry Pi.
#
#    MFRC522-Python is free software: you can redistribute it and/or modify
#    it under the terms of the GNU Lesser General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    MFRC522-Python is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU Lesser General Public License for more details.
#
#    You should have received a copy of the GNU Lesser General Public License
#    along with MFRC522-Python.  If not, see <http://www.gnu.org/licenses/>.
#

import RPi.GPIO as GPIO
import MFRC522
import signal
import time

continue_reading = True

# Capture SIGINT for cleanup when the script is aborted
def end_read(signal,frame):
    global continue_reading
    print("Ctrl+C captured, ending read.")
    GPIO.output(LED, GPIO.LOW)
    continue_reading = False
    GPIO.cleanup()

# Hook the SIGINT
signal.signal(signal.SIGINT, end_read)

# Create an object of the class MFRC522
MIFAREReader = MFRC522.MFRC522()

# Welcome message
print("Welcome to the MFRC522 data read example")
print("Press Ctrl-C to stop.")

#Green LED is 18
LED = 18
GPIO.setup(LED, GPIO.OUT)
GPIO.output(LED, GPIO.HIGH)
time.sleep(1)
GPIO.output(LED, GPIO.LOW)

LEDred = 40
GPIO.setup(LEDred, GPIO.OUT)

#addID = 38
#GPIO.setup(addID, GPIO.IN)

motor = 8
GPIO.setup(motor, GPIO.OUT)
p= GPIO.PWM(motor, 50)   # GPIO 38 for PWM with 50Hz
p.start(10)              # Initialization at cycle 10 on the motor
"""
GPIO.output(motor, GPIO.HIGH)
print("motor on")
time.sleep(5)
GPIO.output(motor, GPIO.LOW)
print("motor off")
"""
#Enter in the uid you wish to use as the 'key' for the 'lock'
my_uid = [[162,249,19,28,84]]

# This loop keeps checking for chips. If one is near it will get the UID and authenticate
while continue_reading:
    #time.sleep(1) #wait one second between searching for NFC tags
    # Scan for cards    
    (status,TagType) = MIFAREReader.MFRC522_Request(MIFAREReader.PICC_REQIDL)

    # If a card is found
    if status == MIFAREReader.MI_OK:
        print("Card detected")
    
    # Get the UID of the card
    (status,uid) = MIFAREReader.MFRC522_Anticoll()
    """
    # Check if a button is pressed; to add new keys
    if GPIO.input(addID) is not 1:
        GPIO.output(LED, GPIO.HIGH)
        GPIO.output(LEDred, GPIO.HIGH)
        print("Adding new IDs...")
        # Turn on both LEDs to signify ability to add new IDs
        time.sleep(2)
        # Check to see if an NFC tag is held up to the reader
        if status == MIFAREReader.MI_OK:
            (status,uid) = MIFAREReader.MFRC522_Anticoll()
            # Append new uid to my_uid list of accepted keys
            my_uid.append(uid)
            print("New ID has been added")
            GPIO.output(LED, GPIO.LOW)
            GPIO.output(LEDred, GPIO.LOW)
            time.sleep(1)
            GPIO.output(LED, GPIO.HIGH)
            GPIO.output(LEDred, GPIO.HIGH)
            time.sleep(0.5)
        GPIO.output(LED, GPIO.LOW)
        GPIO.output(LEDred, GPIO.LOW)
        
    """
    # If we have the UID, continue
    if status == MIFAREReader.MI_OK:
        
        #Prints uid of NFC tag
        #print(uid)
        
        
        
        # Print UID (imported code)
        print("Card read UID: %s,%s,%s,%s" % (uid[0], uid[1], uid[2], uid[3]))
        
        
        # This is the default key for authentication
        #key = [0xFF,0xFF,0xFF,0xFF,0xFF,0xFF]
        
        # Select the scanned tag
        MIFAREReader.MFRC522_SelectTag(uid)
        
        # Check to see if the uid on the card matches my_uid.
        if uid in my_uid:
            #print ("Ya in buddy")
            GPIO.output(LED, GPIO.HIGH) #turns on LED
            p.ChangeDutyCycle(5)        #turns motor to Cycle 5 based on 50Hz movement
            #p.ChangeDutyCycle(0)
            print("Access Granted")
            time.sleep(3)               #wait 3 sec for person to open lock
            GPIO.output(LED, GPIO.LOW)  #turns off LED
            p.ChangeDutyCycle(10)       #turns motor to Cycle 10 based on 50Hz movement
            time.sleep(1)               #waits 1 more second
            p.ChangeDutyCycle(0)
            
            
        else:
            print("Does not work")
            GPIO.output(LEDred, GPIO.HIGH) #turns on red LED
            time.sleep(2)                  #wait 2 seconds
            GPIO.output(LEDred, GPIO.LOW)  #turns off red LED
            
#Enable manual inturruption with keyboard
"""
except KeyboardInterrupt:
    p.stop()
    GPIO.cleanup()
    print("stopping")
"""

"""
        # Authenticate
        status = MIFAREReader.MFRC522_Auth(MIFAREReader.PICC_AUTHENT1A, 8, key, uid)

        # Check if authenticated
        if status == MIFAREReader.MI_OK:
            MIFAREReader.MFRC522_Read(8)
            MIFAREReader.MFRC522_StopCrypto1()
        else:
            print("Authentication error")

"""