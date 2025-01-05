# README for Backend (GreedyCoinAPI)

## GreedyCoinAPI - Backend Service
The `GreedyCoinAPI` is a RESTful API for calculating the minimum number of coins required to achieve a target amount using specified coin denominations. This service is built with **Java**, **Dropwizard**, and Docker for containerization.

---

## Technologies Used
1. **Java**: Core language for backend logic.
2. **Dropwizard**: Lightweight Java framework for RESTful services.
3. **Docker**: Containerization for deployment.
4. **Maven**: Dependency and build management.
5. **Jakarta RESTful Web Services**: For REST endpoints.

---

## VM Setup and Prerequisites

### 1. Provision a VM on Cloud
1. Launch a compute instance on Cloud.
2. Ensure the instance has public access with proper ingress rules:
   - Port `8080` for the backend.
   - Port `3000` for the frontend.

### 2. SSH into the Instance
```bash
ssh -i <your_private_key.pem> opc@<VM_PUBLIC_IP>
```

### 3. Update System Packages
```bash
sudo apt update && sudo apt upgrade -y
```

### 4. Install Docker
```bash
sudo apt install -y docker.io
sudo systemctl start docker
sudo systemctl enable docker
```

### 5. Install Git
```bash
sudo apt install -y git
```

### 6. Add Current User to Docker Group
```bash
sudo usermod -aG docker $USER
```

---

## Deployment Instructions

### 1. Clone the Backend Repository
```bash
git clone https://github.com/christian-quah/GreedyCoinAPI.git
cd GreedyCoinAPI
```

### 2. Update Configuration
Edit the `config.yml` file to set the appropriate allowed origin:
```yaml
allowedOrigins: "http://<your-frontend-url>"
```

### 3. Build and Run the Docker Image
```bash
docker build -t greedy-coin-backend .
docker run -d -p 8080:8080 --name greedy-backend greedy-coin-backend
```

### 4. Test the API
```bash
curl -X POST -H "Content-Type: application/json" \
     -d '{"targetAmount":7.03,"coinDenominations":[0.01,0.5,1.0,5.0,10.0]}' \
     http://<VM_PUBLIC_IP>:8080/api/coins/calculate
```

---

## Notes
- **Ingress Rules**: Ensure the Cloud instance allows traffic on the required ports.
- **Scaling**: For production, consider securing endpoints with HTTPS.
- **Docker Cleanup**: Use `docker ps` and `docker rm` to manage containers as needed.

---

