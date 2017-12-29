from .facemodel import facemodel
import numpy as np
import cv2

label = ['cow', 'cat']


def checkcow(image, img_row=50, img_cols=50):
    try:

        model = facemodel(img_row, img_cols)
        print("UUPPUPUPUPUPUPU")
        model.load_weights("D:\\MyApplication\\Backend\\opencv\\weights.h5")
        print("LOLOLOLOOLOLOL")
        image = cv2.resize(image, (img_row, img_cols))
        # cv2.imshow("xyz",image)

        image = np.reshape(image, [1, 50, 50, 1])

        prediction = model.predict_classes(image)

        index = prediction[0][0]

        prediction = label[index]

        # print("Summary  wdawdawd wdadad wadaaw: ", model.summary())

        # summary = model.summary()

        result = {
            'pred': prediction,
            # 'summary':summary
        }

        return result

    except Exception as e:
        print(str(e))
        return {'error': str(e)}
