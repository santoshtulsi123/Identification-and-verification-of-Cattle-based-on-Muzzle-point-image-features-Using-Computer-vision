# This module will find the cow face using haar cascade
import cv2


def match_face(image):
    img = cv2.imread(image, 0)
    custom_casc = r'C:\Users\try\Desktop\haar_cascade\uglies\cascade.xml'
    cow_face = cv2.CascadeClassifier(custom_casc)
    faces = cow_face.detectMultiScale(
        img,
        scaleFactor=1.017,
        minNeighbors=2,
        minSize=(5, 5)
        # flags = cv2.CV_HAAR_SCALE_IMAGE
    )
    flag = False
    if len(faces) > 0:
        flag = True

    print(len(faces))
    for (x, y, w, h) in faces:
        cv2.rectangle(img, (x, y), (x + w, y + h), (0, 255, 0), 2)
        # print(flag)
    # cv2.imshow("Faces found", img)
    # cv2.waitKey(0)
    return flag
