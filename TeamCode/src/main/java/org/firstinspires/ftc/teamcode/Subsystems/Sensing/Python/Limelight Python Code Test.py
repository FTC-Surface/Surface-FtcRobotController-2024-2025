import cv2
import numpy as np

# global variables go here:
testVar = 0

def drawDecorations(image):
    cv2.putText(image, 
        'Limelight python script!',
        (0, 230),
        cv2.FONT_HERSHEY_SIMPLEX,
        .5, (0, 255, 0), 1, cv2.LINE_AA)

# runPipeline() is called every frame by Limelight's backend.
def runPipeline(image, llrobot):
    img_hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)

    img_hsv_blurred = cv2.GaussianBlur(img_hsv,(5,5),0)

    red_lower_limit_one = (0, 100, 100)
    red_upper_limit_one = (10, 255, 255)

    red_lower_limit_two = (170, 100, 100)
    red_upper_limit_two = (179, 255, 255)

    img_red_threshold_one = cv2.inRange(img_hsv_blurred, red_lower_limit_one, red_upper_limit_one)
    img_red_threshold_two = cv2.inRange(img_hsv_blurred, red_lower_limit_two, red_upper_limit_two)

    yellow_lower_limit = (20, 50, 90)
    yellow_upper_limit = (60, 255, 255)

    blue_lower_limit = (110, 50, 50)
    blue_upper_limit = (130, 255, 255)

    img_yellow_threshold = cv2.inRange(img_hsv_blurred, yellow_lower_limit, yellow_upper_limit)
    img_blue_threshold = cv2.inRange(img_hsv_blurred, blue_lower_limit, blue_upper_limit)
    img_red_threshold = cv2.bitwise_or(img_red_threshold_one, img_red_threshold_two)

    contours, _ = cv2.findContours(img_yellow_threshold, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    largestContour = np.array([[]])
    llpython = [0,0,0,0,0,0,0,0]

    distance = 0
    center_x, center_y = 0, 0

    height, width = image.shape[:2]
    crosshair_x, crosshair_y = width // 2, height // 2

    centroid_x, centroid_y = 0, 0

    closestDistance = 100

    if len(contours) > 0:
        cv2.drawContours(image, contours, -1, (0, 255, 0), 2)
        closestContour = max(contours, key=cv2.contourArea)

        for contour in contours:

            M = cv2.moments(contour)

            if M["m00"] != 0:
                center_x = int(M["m10"] / M["m00"])
                center_y = int(M["m01"] / M["m00"])

            distance = np.sqrt((crosshair_y-center_y) ** 2 + (crosshair_x-center_x) ** 2)

            if distance < closestDistance:
                closestContour = contour
                closestDistance = distance

        x,y,w,h = cv2.boundingRect(closestContour)

        cv2.rectangle(image,(x,y),(x+w,y+h),(0,255,255),2)
        llpython = [1,x,y,w,h,9,8,7]

    # make sure to return a contour,
    # an image to stream,
    # and optionally an array of up to 8 values for the "llpython"
    # networktables array

    return largestContour, image, llpython


