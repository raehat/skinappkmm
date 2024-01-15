# TWACHA

Twacha is a mobile app available on both Android and iOS platforms, designed for the early detection of skin cancers. Imagine you've just returned from a vacation spent on the beaches, and a few weeks later, you notice a peculiar dark spot on your skin that wasn't there before.

In most cases, it might be something harmless, but there's a less probable chance that it could be a form of skin cancer. Twacha can assist in the early detection of skin cancers, providing you with valuable information to help you decide if consulting a doctor is necessary. While consulting a doctor is always the first step, Twacha can help you make sure whether the skin lesion is indeed a potential concern, allowing you to schedule that doctor's appointment you might have postponed due to a busy schedule.

*It's worth noting that Twacha's results may not always be definitive. Therefore, it is essential to use the app as a supplementary tool and not as a substitute for professional medical advice.*

# How is Twacha built

Twacha is built using kotlin multiplatform support and [compose multiplatform]([url](https://www.jetbrains.com/lp/compose-multiplatform/)https://www.jetbrains.com/lp/compose-multiplatform/)
Almost all code is shared and in kotlin. 

# How to run Twacha

Twacha is a kotlin based project. However, it has authentication included as well as it stores data on mongoDB. So there is a backend written in node js for Twacha as well. 
The backend is deployed on vercel. You can find the backend code here https://github.com/raehat/twachabackend. The code is deployed on https://skinappkmmbackend.vercel.app/ 

To analyze image and produce results, a ML model is written in python and compiled with pytorch. 
You can find the jupyter notebook here https://drive.google.com/drive/folders/1DjtGW9PrzQrFjsMVBr1db6qtj3sjGc5J?usp=sharing 

ML Model is deployed in a flask app. Flask app is deployed on railway and Twacha app communicates with this flask app to analyze images with skin lesions. 
Flask App code can be found here https://github.com/raehat/twacha-ML-backend
Flask app is deployed on https://flask-production-9f74.up.railway.app/

