# this file will find the confidence value of cow faces using training and testing images
import cv2
import os
import numpy as np
from openpyxl import Workbook


def find_face():
    # Get user supplied values
    images = []
    labels = []
    flag = None
    imagePath = r"C:\Users\try\Desktop\haar_cascade\img"
    print(imagePath)
    custcasc = r'C:\Users\try\Desktop\haar_cascade\uglies\cascade.xml'
    # TODO: fix directory
    # Create the haar cascade
    faceCascade = cv2.CascadeClassifier(custcasc)
    k = 0
    # Read the image
    for img in os.listdir(imagePath):
        image = cv2.imread(imagePath + r"\\" + img, 0)
        # gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        # print(type(image))
        # Detect faces in the image
        faces = faceCascade.detectMultiScale(
            image,
            scaleFactor=1.017,
            minNeighbors=2,
            minSize=(5, 5)
            # flags = cv2.CV_HAAR_SCALE_IMAGE
        )

        # print("Found {0} faces!".format(len(faces)))
        flag = False
        if len(faces) > 0:
            flag = True

        # Draw a rectangle around the faces
        for (x, y, w, h) in faces:
            k += 1
            labels.append(int(str(img).split("-")[0]))
            images.append(image[y: y + h, x: x + w])
            cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
            # print(flag)
            # cv2.imshow("Faces found", image)
            # cv2.waitKey(0)
    reco = cv2.face.createLBPHFaceRecognizer()
    print(labels)
    reco.train(images, np.array(labels))

    return predict(reco)


#####
# image_paths = [os.path.join(path, f) for f in os.listdir(path) if f.endswith('.sad')]
def predict(recognizer):
    try:
        wb = Workbook()
        ws = wb.active
        confidence = []
        labels = []
        confs = []
        multilist = []
        avg = 0
        i = 0
        j = 0
        ws.append(["Image Number", "Confidence"])

        # recognizer = cv2.face.createLBPHFaceRecognizer()
        faceCascade = r'C:\Users\try\Desktop\haar_cascade\uglies\cascade.xml'
        faceCascade = cv2.CascadeClassifier(faceCascade)
        image_paths = r"C:\Users\try\Desktop\haar_cascade\testing"
        for image_path in os.listdir(image_paths):
            predict_image_pil = cv2.imread(image_paths + "\\" + image_path, 0)
            predict_image = np.array(predict_image_pil, 'uint8')
            faces = faceCascade.detectMultiScale(predict_image, scaleFactor=1.017,
                                                 minNeighbors=2,
                                                 minSize=(5, 5)
                                                 )
            finallabel = None
            for (x, y, w, h) in faces:
                nbr_actual = int(str(image_path).split("-")[0])
                nbr_predicted, conf = recognizer.predict(predict_image[y: y + h, x: x + w])
                if nbr_actual == nbr_predicted:
                    multilist.append(conf)
                    finallabel = nbr_actual
                    i += 1
                    avg += conf
            if len(multilist) != 0:
                print(max(multilist))
                ws.append([finallabel, max(multilist)])
                wb.save('confidence_fac.xlsx')
            multilist.clear()
            # confidence.append(str(nbr_predicted) + "-" + str(confidence))
            # print("{} is Correctly Recognized with {} image with confidence {}".format(nbr_actual, nbr_predicted,
            #                                                                           conf))
            # else:
            # print("{} is Incorrectly Recognized as {} like {}".format(nbr_actual, nbr_predicted, conf))
        print(str(avg / i))
    except Exception as e:
        print(str(e))
        #   return lab, max


find_face()
# print(predict(find_face()))
