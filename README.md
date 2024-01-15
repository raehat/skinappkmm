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
Flask App code can be found here https://github.com/raehat/twacha-ML-backend. Flask app is deployed on https://flask-production-9f74.up.railway.app/

1) Open terminal
2) Run command
   ```git clone https://github.com/raehat/twacha```
3) Run command
   ```cd twacha```
4) Open project in Android Studio

You can build the android app as well as ios app from android studio. 

If you want to run the backend locally, follow through

1) Open terminal
2) Run command
```https://github.com/raehat/twachabackend```
3) Run command
```cd twachabackend```
4) Run command
```npm install```
5) Run command
```npm start```

Node js app will run on http://localhost:3000/. Open Twacha Kotlin Application in Android Studio. Go to Twacha -> composeApp -> src -> commonMain -> kotlin -> Data -> Network.kt

You will find AUTH_URL here  
![Screenshot 2024-01-16 023428](https://github.com/raehat/twacha/assets/77321971/969e9358-26e4-4384-9aad-482c323e4830)

Replace original value of AUTH_URL with http://localhost:3000/

If you want to run flask app with ML Model locally, follow through

1) Open terminal
2) Run command
```https://github.com/raehat/twacha-ML-backend```
3) Run command
```twacha-ML-backend```
4) Run command
```pip install requirementx.txt```
5) Run command
```python app.py```

Flask App will run on http://localhost:5000/. Open Twacha Kotlin Application in Android Studio. Go to Twacha -> composeApp -> src -> commonMain -> kotlin -> Data -> Network.kt

You will find ML_URL here  
![Screenshot 2024-01-16 023428](https://github.com/raehat/twacha/assets/77321971/1042adfc-a21a-4407-8434-e7372d2b038b)

Replace original value of ML_URL with http://localhost:3000/
