# 👀 2023 Solution Challenge: ImReader 👀
[2023 Solution challenge Demo Video of ImReader]()

## ☘️ What's ImReader?
ImReader is the solution to solve the 10th goal of the [UN's SDGs](https://sdgs.un.org/), Reduced Inequalities. It's a service for people who use the Voice Assistant program (iPhone is Voice over, Android is Voice Assistant), such as a visual impairment. 

The Voice Asthma program is especially helpful for blind people to use smartphones. However, some images cannot be read and simply described as "detailed images." Because of this, they have more difficulty in acquiring information than others.

To solve this problem, We developed a service called ImReader! If voice assistant program that we developed ourselves recognizes an image, it does not inform it as a "detailed image" and extracts the text from the image by inserting the image into the Deep Learning model(OCR). This text is then told to the user using the TTS API. Through this process, there is an advantage that the user can access not only the plain text but also the text in the image.

<br>

## ☘️ How to Use
We really tried to complete the service, but unfortunately we didn't finish it on time. So we'll show you a prototype of UI and a communication results between the server and the model.

- Prototype (Situation: When ordering)
  <p align="center">
    <img src="https://user-images.githubusercontent.com/90444862/229042507-ed940ea8-76da-41c7-ba6e-c2536336c1a3.gif" width=150>
    <img src="https://user-images.githubusercontent.com/90444862/229042875-a029c80e-45bd-4da1-8334-ea4a68d54d57.gif" width=150>
    <img src="https://user-images.githubusercontent.com/90444862/229043216-fca68677-1246-40eb-948b-89d348863cc8.png" width=150>
    <img src="https://user-images.githubusercontent.com/90444862/229042937-acb1e6e7-4c98-47d7-ab9a-72906ee17d94.gif" width=150>
    <img src="https://user-images.githubusercontent.com/90444862/229042995-26d4b789-2073-46fc-8db1-f397379a8112.gif" width=150>
  </p>
  
  1. Turn off the Voice Assistant built into the System to use ImReader.
  2. Users look around the app to order the food they want
  3. If there is a character in the image, the character recognized by the model is heard to the user using the TTS.
  4. Users proceed with the rest including payment

- Communication Result (Server and the model)
<p align="center">
  <img src="https://user-images.githubusercontent.com/90444862/229028597-3315d37a-e8a7-483a-9cef-ddb0ce456bca.gif" width=800>
</p>

  You can test it by following the steps!
  
  1. Please go to [Postman](https://www.postman.com/).
  2. Please set the link to http://35.234.33.62/img-src .
  3. Please send the base64 code in the following format to the body.
      {
	        "base64": "base64-string"
      }
  4. Press Send to get the corresponding results.
  
<br>

## ☘️ Used Technology & Architecture
![image](https://user-images.githubusercontent.com/90444862/229029705-ae0ee800-e795-49bd-9076-832e4920d1be.png)

1. The client implemented as Kotlin sends the image information to base64. 
2. The server then uses the virtual machine in GCP to run the OCR model with that information. 
3. When the returned result value is sent back to the client.
4. The client hears the text to the user through the TTS API.

<br>

## ☘️ Team Helppy

<table align="center">
   <tr>
      <td colspan="1" align="center"><strong>[ Android ]</strong></td>
      <td colspan="1" align="center"><strong>[ Back-End ]</strong></td>
      <td colspan="1" align="center"><strong>[ Deep Learning ]</strong></td>
      <td colspan="1" align="center"><strong>[ Deep Learning ]</strong></td>
   </tr>
  <tr>
    <td align="center">
    <a href="https://github.com/yoouung"><img src="https://avatars.githubusercontent.com/u/78146904?v=4" width="180px" alt="Park Jaeyoung"/><br/><sub><b>Park Jaeyoung</b></sub></a><br />
    </td>
     <td align="center">
        <a href="https://github.com/nathn00"><img src="https://avatars.githubusercontent.com/u/89184540?v=4" width="180px" alt="Park Injae"/><br/><sub><b>Park Injae</b></sub></a>
     </td>
     <td align="center">
        <a href="https://github.com/drizzle0171"><img src="https://avatars.githubusercontent.com/u/90444862?v=4" width="180px" alt="Lee Seulbi"/><br/><sub><b>Lee Seulbi</b></sub></a>
     </td>
         <td align="center">
        <a href="https://github.com/gumchinjun"><img src="https://avatars.githubusercontent.com/u/97167373?v=4" width="180px" alt="Jeon Junseok"/><br /><sub><b>Jeon Junseok</b></sub></a>
     </td>
  </tr>
</table>
