import random

def gen_id(cid):

    data_id = random.randint(0, 2**32)
    data_id = cid + '_' +str(data_id)
    print(data_id)
    return data_id
