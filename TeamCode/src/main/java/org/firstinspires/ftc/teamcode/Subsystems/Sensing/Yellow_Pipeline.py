import cv2
import numpy as np
import math

# global variables go here:



def drawDecorations(image):
    cv2.putText(image,
        'Limelight python script!',
        (0, 230),
        cv2.FONT_HERSHEY_SIMPLEX,
        .5, (0, 255, 0), 1, cv2.LINE_AA)

# runPipeline() is called every frame by Limelight's backend.
def runPipeline(image, llrobot):
    img_hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)

    red_lower_limit_one = (0, 100, 100)
    red_upper_limit_one = (10, 255, 255)

    red_lower_limit_two = (170, 100, 100)
    red_upper_limit_two = (179, 255, 255)

    img_red_threshold_one = cv2.inRange(img_hsv, red_lower_limit_one, red_upper_limit_one)
    img_red_threshold_two = cv2.inRange(img_hsv, red_lower_limit_two, red_upper_limit_two)

    yellow_lower_limit = (20, 100, 100)
    yellow_upper_limit = (30, 255, 255)

    blue_lower_limit = (90, 30, 20)
    blue_upper_limit = (130, 255, 255)

    img_yellow_threshold = cv2.inRange(img_hsv, yellow_lower_limit, yellow_upper_limit)
    img_blue_threshold = cv2.inRange(img_hsv, blue_lower_limit, blue_upper_limit)
    img_red_threshold = cv2.bitwise_or(img_red_threshold_one, img_red_threshold_two)

    contours, _ = cv2.findContours(img_yellow_threshold, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    largestContour = np.array([[]])
    llpython = [0,0,0,0,0,0,0,0]

    testVar = 0
    verticies = [0, 0, 0, 0]
    angle = 0

    if len(contours) > 0:
        cv2.drawContours(image, contours, -1, (0, 255, 0), 2)
        largestContour = max(contours, key=cv2.contourArea)
        epsilon = 0.02 * cv2.arcLength(largestContour, True)
        approx = cv2.approxPolyDP(largestContour, epsilon, True)


        for contour in contours:

            M = cv2.moments(contour)

            if M["m00"] != 0:
                center_x = int(M["m10"] / M["m00"])
                center_y = int(M["m01"] / M["m00"])

            distance = np.sqrt((crosshair_y-center_y) ** 2 + (crosshair_x-center_x) ** 2)

            if distance < closestDistance:
                largestContour = contour
                closestDistance = distance



        if len(approx) == 4:

            for point in approx:
                x1, y1 = point[0]
                cv2.circle(image, (x1, y1), 5, (0, 0, 255), -1)
                verticies[testVar] = [x1, y1]
                testVar += 1

            angle = math.atan(abs(verticies[0][1] - verticies[1][1]) / abs(verticies[0][0] - verticies[1][0]))
            degrees = angle * (180/math.pi)
            testVar = 0

            print(verticies)

            cv2.putText(image, str(degrees), (100, 100), cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 0), 2, cv2.LINE_AA)

            cv2.putText(image, ', '.join(map(str, verticies[0])), verticies[0], cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 0), 2, cv2.LINE_AA)
            cv2.putText(image, ', '.join(map(str, verticies[1])), verticies[1], cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 0), 2, cv2.LINE_AA)
            cv2.putText(image, ', '.join(map(str, verticies[2])), verticies[2], cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 0), 2, cv2.LINE_AA)
            cv2.putText(image, ', '.join(map(str, verticies[3])), verticies[3], cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 0), 2, cv2.LINE_AA)


        x,y,w,h = cv2.boundingRect(largestContour)

        cv2.rectangle(image,(x,y),(x+w,y+h),(0,255,255),2)
        llpython = [1,x,y,w,h,9,8,7]


    # make sure to return a contour,
    # an image to stream,
    # and optionally an array of up to 8 values for the "llpython"
    # networktables array

    return largestContour, image, llpython, degrees
