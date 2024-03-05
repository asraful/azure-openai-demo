## Motivation

> Introduction by ChatGpt
>
>In the realm of AI innovation, Azure's integration with OpenAI models offers unparalleled potential for transformative solutions. By leveraging the power of OpenAI's cutting-edge technology, users can now bring their own data into the fold, empowering them to tailor AI models to their unique needs and objectives. One particularly promising tool in this arsenal is RAG (Retrieval-Augmented Generation), which marries the capabilities of retrieval-based and generative models to produce contextually rich and coherent outputs. Imagine a world where information retrieval seamlessly fuels the creative process, generating insightful, nuanced content with unmatched accuracy and relevance. Through this demonstration, we aim to showcase the boundless possibilities of this synergistic approach, illustrating how it can revolutionize industries ranging from content creation to customer service, ushering in a new era of AI-enabled innovation and productivity. Join us as we embark on a journey to unlock the full potential of AI, where imagination meets data-driven precision to shape a brighter future for all.
>


![Screenshot 2024-03-04 at 16 06 42](https://github.com/asraful/azure-openai-demo/assets/284564/5dc5e17f-9dae-4100-97a6-d3563a953307)


## Azure resource provisioning (manual steps)

### Navigate to Azure AI Studio
  * deploy model : gpt4
  * deploy model : text-embedding-ada-002
### Create azure search resource 
  * using template : /azure-infra   !! Manually for now !
### Upload data 
  * Navigate to Azure AI Studio > Chat > Add Data
  * Using upload file option(data/data_en.pdf) 
  * Select index (pre-created)
  * select text-embedding-ada-002, enable vectorization 

### Replace with proper values (application.properties)
```
   spring.ai.azure.openai.api-key=<replace with key>
   spring.ai.azure.openai.endpoint=https://xxxxx.openai.azure.com/
   spring.ai.azure.openai.chat.model=gpt4
   app.azure.cognitive.search.index=<replace with index name>
   app.azure.cognitive.search.endpoint=https://xxxxx.search.windows.net
   app.azure.cognitive.search.key=<replace with key>
   app.azue.cognitive.search.user.provided.data.vectorizationSource=text-embedding-ada-002
  ```


## Deplyment guide 

> by ChatGpt

Here's a step-by-step deployment guide for your multi-service application using Docker Compose:

### Prerequisites:

* Docker installed on your machine.
* Docker Compose installed on your machine.
* Access to the Docker images specified in your Docker Compose file **(demoworkeracr.azurecr.io/azure-openai-demo-frontend, demoworkeracr.azurecr.io/azure-openai-demo-backend, demoworkeracr.azurecr.io/azure-openai-demo-nginx)**.
### Steps:

#### Clone the Repository:
Clone the repository containing your Docker Compose file and application source code if you haven't already done so.

Build Docker Images:
Navigate to the root directory of your project containing the Docker Compose file.


```
cd your_project_directory
```
Then, run the following command to build the Docker images defined in the Docker Compose file:


```
docker-compose build
```
Run the Application:
Once the images are built, you can start the application using Docker Compose:


```
docker-compose up -d
```
This command will start all the services defined in the Docker Compose file in detached mode.

#### Access the Application:

* The frontend application will be accessible at http://localhost:3000.
* The backend application will be accessible at http://localhost:8080.
* The NGINX service will be accessible at http://localhost.

### Monitoring and Troubleshooting:

To monitor the logs of all services, you can use:

```
docker-compose logs -f
```
If there are any issues, you can troubleshoot by inspecting the logs for each service.
Stopping the Application:
To stop the application and shut down the services, run:

```
docker-compose down
```
### Optional: Customizing NGINX Configuration:
If you need to customize the NGINX configuration, you can edit the nginx.conf file in the **./nginx** directory before building the NGINX image.

### Security Considerations:

* Ensure that your Docker images and dependencies are up to date to mitigate security vulnerabilities.
* Secure sensitive data such as credentials and API keys.
* Implement proper network security configurations.

### Scaling:

You can scale individual services by running multiple containers of the same service using Docker Compose scale command.

## ToDo 
* Introduce authentication
* Add new AzureOpenAI API capability 
* Introduce more use cases
* Automate infrastructure provisioning
* Automate deployemnt using CI/CD
  
## Reference 

  * [ChatCompletionsWithYourData](https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/openai/azure-ai-openai/src/samples/java/com/azure/ai/openai/ChatCompletionsWithYourData.java)
  * [OpenAI Search Java Demo](https://www.youtube.com/watch?v=mcftrg6L8Fs)
  * [azure-search-openai-demo-java](https://github.com/Azure-Samples/azure-search-openai-demo-java/tree/main)
  * [Hybrid search using vectors and full text in Azure AI Search](https://learn.microsoft.com/en-us/azure/search/hybrid-search-overview)
  * [Chat with Azure OpenAI models using your own data](https://learn.microsoft.com/en-us/azure/ai-services/openai/use-your-data-quickstart?tabs=command-line%2Cpython-new&pivots=programming-language-spring)


## Open Issues 
  * [Static Deployment Issue on Azure](https://github.com/vercel/next.js/discussions/62242)
  
