# matching of uploaded cow nose and the image form database and return result true or false based on matching
import numpy as np
import cv2
from matplotlib import pyplot as plt


def matching_nose(mid, filename):
    # def matching_nose():
    # try:
    # TODO: fix directory
    img1 = cv2.imread(mid, 0)  # queryImage
    # img1 = cv2.imread(str(filename) + ".jpg", 0)  # queryImage
    # img2 = cv2.imread(r'' + str(cid) + '.JPG', 0)  # trainImage

    img2 = cv2.imread(filename, 0)  # queryImage
    # img2 = cv2.imread('C:\\Users\\Devin\\Desktop\\muzzle point image pattern\\' + str(mid), 0)
    # cv2.imshow('test', img2)
    # print(filename, cid)
    flag = 0
    #print(type(img1))
    #print(type(img2))

    # img1 = cv2.imread(filename, 0)  # queryImage
    # img2 = cv2.imread(r'' + str(cid) + '.JPG', 0)  # trainImage
    # print(filename, cid)
    # flag = False

    # Initiate SIFT detector
    # avging = cv2.blur(img1, (10, 10))  # You can change the kernel size as you want
    # avging1 = cv2.blur(img2, (10, 10))  # You can change the kernel size as you want
    # print("Averaging")
    # flag = matching_sift(avging, avging1)

    # Gaussian Blurring
    # gausBlur = cv2.GaussianBlur(img1, (5, 5), 0)  # Again, you can change the kernel size
    # gausBlur1 = cv2.GaussianBlur(img2, (5, 5), 0)  # Again, you can change the kernel size
    # print("\nGaussian")
    # flag = matching_sift(gausBlur, gausBlur1)

    # Median blurring
    # medBlur = cv2.medianBlur(img1, 5)
    # medBlur1 = cv2.medianBlur(img2, 5)
    # print("\nMedian")
    # flag = matching_sift(medBlur, medBlur1)

    # Bilateral Filtering
    bilFilter = cv2.bilateralFilter(img1, 9, 75, 75)
    bilFilter1 = cv2.bilateralFilter(img2, 9, 75, 75)
    #print("\nBilateral")
    flag = matching_sift(bilFilter, bilFilter1)

    # Histogram
    # equ = cv2.equalizeHist(img1)
    # equ1 = cv2.equalizeHist(img2)

    # print("\nHistogram")
    # flag = matching_sift(equ, equ1)

    # print(flag)

    # clahe = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8, 8))
    # print("\nclahe")
    # cl1 = clahe.apply(img1)
    # cl11 = clahe.apply(img2)
    # flag = matching_sift(cl1, cl11)
    # print("\nOriginal")
    # matching_sift(img1, img2)
    return flag
    # except Exception as e:
    # print(str(e))


def matching_sift(img1, img2):
    # try:
    # Initiate SIFT detector
    sift = cv2.xfeatures2d.SIFT_create()

    # find the keypoints and descriptors with SIFT
    kp1, des1 = sift.detectAndCompute(img1, None)
    kp2, des2 = sift.detectAndCompute(img2, None)
    #print("Image 1: " + str(len(des1)) + "  \t Image 2 : " + str(len(des2)))
    # FLANN parameters
    FLANN_INDEX_KDTREE = 0
    index_params = dict(algorithm=FLANN_INDEX_KDTREE, trees=5)
    search_params = dict(checks=50)  # or pass empty dictionary

    flann = cv2.FlannBasedMatcher(index_params, search_params)
    low = None
    matches = flann.knnMatch(des1, des2, k=2)
    if len(des1) < len(des2):
        low = len(des1)
    else:
        low = len(des2)

    # Need to draw only good matches, so create a mask
    matchesMask = [[0, 0] for i in range(len(matches))]
    j = 0
    # ratio test as per Lowe's paper
    for i, (m, n) in enumerate(matches):
        if m.distance < 0.9 * n.distance:
            matchesMask[i] = [1, 0]
            j += 1

    draw_params = dict(matchColor=(0, 255, 0),
                       singlePointColor=(255, 0, 0),
                       matchesMask=matchesMask,
                       flags=0)
    #print("Matched Points " + str(j))
    img3 = cv2.drawMatchesKnn(img1, kp1, img2, kp2, matches, None, **draw_params)
    #print("Result " + str((j / low) * 100))
    # plt.imshow(img3, ), plt.show()
    # print(j)
    return (j / low) * 100
    # except Exception as e:
    # print(str(e))

    # matching_nose('C1001.JPG','C:\\Users\\Devin\\Desktop\\muzzle point image pattern\\C1001')
    # matching_nose(2,True)
