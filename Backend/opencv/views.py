from django.http import HttpResponse
from rest_framework.views import APIView
from rest_framework.response import Response
from .models import user, userdata, user_register_request, cattle_reg_appliction_req, cattle_fa, cattle
from .serializers import userserializer, cattleserializer, loginserializer
import json
import base64
from .cowpredict import checkcow
import cv2
import shutil
from .randid import gen_id
import os
from .matching import matching_nose
from .match_cow_face import match_face

image_dir = "C:\\Users\\Devin\\Desktop\\cattle_detection\\opencv_cow\\images\\"
main = "C:\\Users\\Devin\\Desktop\\cattle_detection\\opencv_cow\\"
tempdir = "C:\\Users\\Devin\\Desktop\\cattle_detection\\opencv_cow\\tempdirs\\"
# url login
class login(APIView):
    def post(self, request):
        req_str = str((request.body).decode('utf-8'))
        json_data = json.loads(req_str)
        # json_data = json.load(request)
        print(json_data)
        uname = json_data['uname']
        password = json_data['password']
        print(uname + password)
        login_database = user_register_request.objects.all()

        for data in login_database:
            if data.fname == uname and data.password == password:
                serializer = loginserializer(data)
                return Response({"result": "True", "data": serializer.data})

        return Response({"result": "FALSE"})


# url is name
class namelist(APIView):

    def post(self, request):
        # name = request.data['fname']
        req_str = str((request.body).decode('utf-8'))
        jso = json.loads(req_str)
        print(jso)
        # print(jso['encoded'])
        # for a in names:
        #   if a.fname == name:
        #      return Response(testingserializer(a).data)

        faceimage = base64.b64decode(jso['face'])
        muzzleimage1 = base64.b64decode(jso['muzzle1'])
        muzzleimage2 = base64.b64decode(jso['muzzle2'])
        muzzleimage3 = base64.b64decode(jso['muzzle3'])
        muzzleimage4 = base64.b64decode(jso['muzzle4'])
        userid = jso['uid']

        # print(userid+" UID")
        # names = user.objects.all()

        name = user_register_request.objects.get(uid=userid)
        # print(name.lname)
        cows_id = name.cattle_reg_appliction_req_set.all()
        #print(cows_id)
        dir_id = gen_id(userid)
        print(dir_id)
        path = tempdir + dir_id
        os.makedirs(path, 0o777)

        face_image = 'face.jpg'  # I assume you have a way of picking unique filenames
        os.path.join(path, face_image)
        face_img_path = path + "\\" +face_image
        with open(face_img_path, 'wb') as f:
            f.write(faceimage)

        muzzle_image = 'muzzle1.jpg'
        os.path.join(path, muzzle_image)
        muzzle1_img_path = path + "\\" +muzzle_image
        f = open(muzzle1_img_path, "wb")
        f.write(muzzleimage1)

        muzzle_image = 'muzzle2.jpg'
        os.path.join(path, muzzle_image)
        muzzle2_img_path = path + "\\" +muzzle_image
        f = open(muzzle2_img_path, "wb")
        f.write(muzzleimage2)

        muzzle_image = 'muzzle3.jpg'
        os.path.join(path, muzzle_image)
        muzzle3_img_path = path + "\\" +muzzle_image
        f = open(muzzle3_img_path, "wb")
        f.write(muzzleimage3)

        muzzle_image = 'muzzle4.jpg'
        os.path.join(path, muzzle_image)
        muzzle4_img_path = path + "\\" +muzzle_image
        f = open(muzzle4_img_path, "wb")
        f.write(muzzleimage4)

        img = cv2.imread(face_img_path, 0)
        pred_results = checkcow(image=img)

        if str(pred_results["pred"]) == "cow":
            # Do the Matching Nose Operation
            print("Keras..")

        else:
            print("Error..")
            # Send error that given image is not a cow image

def test(request):
    return HttpResponse("<h1>TESTING</h1>")

# url is register for user
class request_register(APIView):
    def post(self, request):
        try:
            req_str = (request.body).decode('utf-8')
            json_data = json.loads(req_str)
            print(json_data)
            fname = json_data["fname"]
            mname = json_data["mname"]
            lname = json_data["lname"]
            aadhar = json_data["aadhar"]
            mobile = json_data["mobile"]
            password = json_data["password"]
            address = json_data["address"]
            uid = aadhar+fname
            new_data = user_register_request.objects.all()
            #new_data.create(uid=uid, aadhar=aadhar, fname=fname, mname=mname, lname=lname, mobile=mobile, address=address)

            path = image_dir+str(uid)
            if os.path.exists(path):
                return Response({"error":"User Already Exists!"})
            os.makedirs(path, 0o777)
            new_data.create(uid=uid, aadhar=aadhar, fname=fname, mname=mname, lname=lname, mobile=mobile, address=address, password=password)
            return Response({"result": "success"})
        except Exception as e:
            print(str(e))
            return Response({"error":str(e)})


class register_animal(APIView):
    def post(self, request):
        try:
            req_str = (request.body).decode('utf-8')
            json_data = json.loads(req_str)
            uid = json_data["uid"]
            #print(json_data)
            uid = user_register_request.objects.get(uid=uid)
            breed = json_data["breed"]
            color = json_data["color"]
            horn_size = json_data["horn"]
            faceimage = base64.b64decode(json_data["face"])
            #muzzleimage1 = base64.b64decode(json_data["muzzle1"])
            #muzzleimage2 = base64.b64decode(json_data["muzzle2"])
            #muzzleimage3 = base64.b64decode(json_data["muzzle3"])
            #muzzleimage4 = base64.b64decode(json_data["muzzle4"])
            muzzleimages = []
            muzzleimages.append(base64.b64decode(json_data["muzzle1"]))
            muzzleimages.append(base64.b64decode(json_data["muzzle2"]))
            muzzleimages.append(base64.b64decode(json_data["muzzle3"]))
            muzzleimages.append(base64.b64decode(json_data["muzzle4"]))

            print("test............................")
            new_data = cattle_reg_appliction_req.objects.all()
            path = image_dir+str(json_data["uid"])
            if os.path.exists(path):
                filename = 'image.jpg'  # I assume you have a way of picking unique filenames
                with open(filename, 'wb') as f:
                    f.write(faceimage)
                src = main+filename
                dest = path
                shutil.move(src, dest)

                for index, img in enumerate(muzzleimages):
                    filename = str(uid) + "_muzzle" + str(index) + ".jpg"
                    with open(filename, 'wb') as f:
                        f.write(img)
                    src = main + filename
                    dest = path
                    shutil.move(src, dest)

            new_data.create(uid=uid, breed=breed, color=color, horn_size=horn_size)

            return Response({"result": "success"})
        except Exception as e:
            print(str(e))
            return Response({"error":str(e)})
