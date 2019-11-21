[![Build Status](https://travis-ci.org/OpenMined/AndroidWorker.svg?branch=master)](https://travis-ci.org/OpenMined/AndroidWorker)

![PySyft for Android](art/pysyft_android.png)

See publication in [PySyft for Android. Extending OpenMined to mobile devices](https://medium.com/@mccorby/pysyft-android-b28da47a767e)

# Android Worker

The Android Worker is an app that connects to a PySyft worker and performs the operations on its behalf. It is part of a setup that uses a socket server, a socket client and the app itself which is another socket client

## Quick start

* Start the Android application
* Start the socket server
  * There is an example provided in the code of PyGrid: [`socketio_server_demo.py`](https://github.com/OpenMined/PyGrid/blob/dev/examples/android/socketio_server_demo.py)
* Start the socketio client
  * See the example [`Android Proxy.ipynb`](https://github.com/OpenMined/PyGrid/blob/dev/examples/android/Android%20Proxy.ipynb)
* Start a Jupyter notebook and create a `WebsocketIOClientWorker` object.
  * Note that you need to provide strategies for serialization and compression
  * See the example [`Socket Bob.ipynb`](https://github.com/OpenMined/PyGrid/blob/dev/examples/android/Socket%20Bob.ipynb)
* Connect the socketio client (Bob)
* Connect the Android app
* Execute the operations in the notebook and see how Android handles them!


#### Notes
* The project is still in an early stage and only some PySyft operations are implemented: `send`, `get`, `add`, `delete`, `mul`
* Make sure the socket server, the client in the notebook and the app all point to the same host/port
* To run the setup locally, it is better to use an Android emulator

## PySfyt Version
This app has been tested with PySyft 0.2.0a2 and PyGrid commit 8f990d9790899c87323a9dc82381b8811439f809


