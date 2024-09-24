## 서비스 시나리오

### 기능적 요구사항

1. 접수자가 접수한다.
2. 접수가 완료되면 의사에게 환자 정보가 등록된다
3. 등록된 정보로 의사가 검사를 처방한다
4. 검사 정보가 생성된다
5. 검사자가 검사를 시행 및 취소한다
6. 의사가 확인한다.
7. 의사가 진료 종료/ 거부를 한다
8. 접수자가 진료 완료,취소를 확인한다

### 비기능적 요구사항

1. 장애격리
   1. 진료 기능이 수행되지 않더라도 진료를 365일 병원 업무 시간 내에는 받을 수 있어야 한다 Async (event-driven), Eventual Consistency
   2. 검사 기능이 수행되지 않더라도 검사를 365일 병원 업무 시간 내에는 받을 수 있어야 한다 Async (event-driven), Eventual Consistency
2. 성능
   1. 환자 관리팀은 현재 고객의 진료 상태를 확인할 수 있어야 한다 CQRS

## 클라우드 네이티브 아키텍처

![클라우드 네이티브 아키텍처](https://github.com/user-attachments/assets/ed8c13ba-3de5-4a4f-99dc-e5548c7914dd)

## 클라우드 네이티브 모델링

![클라우드 네이티브 모델링](https://github.com/user-attachments/assets/70827861-1857-4315-9eee-a6d49b2bb617)

## 클라우드 네이티브 개발

1. 접수자가 진료를 접수한다

![기능 확인_1](https://github.com/user-attachments/assets/273a9c8c-75b9-4107-b666-2d007e7d5018)

2. 접수가 완료되면 의사에게 환자 정보가 등록된다

![기능 확인_2](https://github.com/user-attachments/assets/c63162ed-cce1-400b-be20-d6b83301f6ac)

3. 등록된 정보로 의사가 검사를 처방한다

![기능 확인_3](https://github.com/user-attachments/assets/fab2e57a-9a32-4be1-bbe1-3554c6765aab)

4. 검사 정보가 생성된다

![기능 확인_4](https://github.com/user-attachments/assets/5fda381b-ac25-4391-ab8e-188be62ed67f)

5. 검사자가 검사를 시행 및 취소한다

![기능 확인_5](https://github.com/user-attachments/assets/8278139a-5c35-460b-a3ab-bb88cd13afeb)

6. 의사가 검사 결과를 확인한다

![기능 확인_6](https://github.com/user-attachments/assets/122be4f0-7348-4b10-9e7f-947aff9ad596)

7. 의사가 진료 (종료/ 거부)를 한다

![기능 확인_7](https://github.com/user-attachments/assets/0372521a-2901-4d99-a467-c73a68fd2109)

8. 접수자가 진료 (완료/취소)를 확인한다

![기능 확인_8](https://github.com/user-attachments/assets/76ecd1e5-8881-4d07-8d18-c424ec1f3203)

### SAGA

Choreography 방식 Kafka를 통해 게시-구독 원칙을 적용하여 각 마이크로 서비스는 자체 로컬 트랜잭션을 실행하고 이벤트를 메시지 브로커 시스템 에 게시하고 다른 마이크로 서비스에서 로컬 트랜잭션을 트리거한다

### Compesnation

검사거부, 완료 진료 거부 ,완료시 자동으로 Status 변경

![Compesnation](https://github.com/user-attachments/assets/8af70d21-5a3d-4329-a3bd-280e293bc445)

### GateWay

모든 API는 게이트웨이를 통한 단일 진입점으로 서비스에 접근된다

```
http http://localhost:8088/receptions/1 
http http://localhost:8088/diagnoses/1
http http://localhost:8088/examinations/1
```

### CQRS

다른 도메인의 서비스의데이터 원본에 직접 접근없이 내 서비스에서의 데이터 조회가 가능하다

![CQRS_1](https://github.com/user-attachments/assets/4203bbb9-ea80-4c8c-9345-3e06ecdd1bb8)

![CQRS_2](https://github.com/user-attachments/assets/c6af900e-ad77-45f4-bfa0-516602b5e29f)

![CQRS_3](https://github.com/user-attachments/assets/02775b28-a54d-4a4e-a26f-aac81ef73b02)

```bash
http http://localhost:8085/patientManagements
```

![CQRS_4](https://github.com/user-attachments/assets/b7141ae2-fcd7-4351-a849-4df0b8f97f76)

## 클라우드 배포 - Container 운영

### AzureDevOps를 통해 CI/CD 진행

Pipline Trigger 설정을 통해 GitHub에  Push를 수행할 경우 빌드를 한다

Pipline build script는 서비스별 폴더의 kubernetes/deployment.yaml을 이용한다

![클라우드 배포 - Container 운영_1](https://github.com/user-attachments/assets/80a77015-a340-4d57-84f0-58d96e120b1e)

![클라우드 배포 - Container 운영_2](https://github.com/user-attachments/assets/c69b872f-50a9-47a4-90a8-a23b51b619b0)

![클라우드 배포 - Container 운영_9](https://github.com/user-attachments/assets/0dd6d6a5-6538-49ce-91ec-b298f1c350db)

![클라우드 배포 - Container 운영_10](https://github.com/user-attachments/assets/35a5b4c2-7cad-48d2-9b34-79211a7573f7)

```bash
apiVersion: apps/v1
kind: Deployment
metadata:
  name: diagnosis
  labels:
    app: diagnosis
spec:
  replicas: 1
  selector:
    matchLabels:
      app: diagnosis
  template:
    metadata:
      labels:
        app: diagnosis
    spec:
      containers:
        - name: diagnosis
          image: "user04.azurecr.io/diagnosis:latest"
          ports:
             - containerPort: 8080                   
```

### ACR

![클라우드 배포 - Container 운영_4](https://github.com/user-attachments/assets/33dce065-2ebd-4335-ad05-a06f0cd708ed)

### 쿠버네티스 배포 완료

![클라우드 배포 - Container 운영_3](https://github.com/user-attachments/assets/1a51bb3b-df7b-47bf-89a4-d678a434ecc7)

## 컨테이너 인프라 설계 및 구성 역량

### 컨테이너 자동확장 - HPA

오토 스케일링 설정명령어 호출

```bash
kubectl autoscale deployment diagnoses --cpu-percent=20 --min=1 --max=3
```

배포파일에 CPU 요청에 대한 값을 지정 후 배포한다

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: diagnosis
  labels:
    app: diagnosis
spec:
  replicas: 1
  selector:
    matchLabels:
      app: diagnosis
  template:
    metadata:
      labels:
        app: diagnosis
    spec:
      containers:
        - name: diagnosis
          image: "user04.azurecr.io/diagnosis:latest"
          ports:
             - containerPort: 8080
          resources:
            requests:
              cpu: "200m"
```

#### Auto Scale-Out 증명

새로운 터미널을 열어서 seige 명령으로 부하를 주어서 Pod 가 늘어나도록 한다

```bash
kubectl exec -it siege -- /bin/bash
siege -c20 -t60S -v http://diagnosis:8080/diagnoses
exit
```

![HPA_3](https://github.com/user-attachments/assets/e5fc9fd0-3e4a-426a-bf6f-245e75e0d031)

```bash
kubectl get po -w
```

![HPA_2](https://github.com/user-attachments/assets/4aa042a5-4fd1-4ac0-9d56-630fa2eb0e14)

```bash
kubectl get hpa
```

![HPA_1](https://github.com/user-attachments/assets/5b6cec37-ebd4-450f-96f6-33d37ead4b15)

### 컨테이너로부터 환경분리 - ConfigMap

ConfigMap을 로그레벨 INFO로 쿠버네티스에 배포한다

```yaml
kubectl apply -f - <<EOF
apiVersion: v1
kind: ConfigMap
metadata:
  name: config-dev
  namespace: default
data:
  ORDER_DB_URL: jdbc:mysql://mysql:3306/connectdb1?serverTimezone=Asia/Seoul&useSSL=false
  ORDER_DB_USER: myuser
  ORDER_DB_PASS: mypass
  ORDER_LOG_LEVEL: INFO
EOF
```

![ConfigMap_1](https://github.com/user-attachments/assets/6b9a519a-05cd-4fc3-898c-599edf210113)

배포파일에 설정을 추가하고 배포한다

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: diagnosis
  labels:
    app: diagnosis
spec:
  replicas: 1
  selector:
    matchLabels:
      app: diagnosis
  template:
    metadata:
      labels:
        app: diagnosis
    spec:
      containers:
        - name: diagnosis
          image: "user04.azurecr.io/diagnosis:latest"
          ports:
             - containerPort: 8080
          env:
              - name: ORDER_LOG_LEVEL
                valueFrom:
                  configMapKeyRef:
                      name: config-dev
                      key: ORDER_LOG_LEVEL
```

명령어를 통해서 Pod의 환경변수를 확인한다

```bash
kubectl exec pod/diagnosis-84f566d7db-p82rx -- env
```

Log Level이 INFO로 설정되어 있다

![ConfigMap_2](https://github.com/user-attachments/assets/75a847d3-1013-43c1-ac82-7f43ae902c44)

### 클라우드스토리지 활용 - PVC

아래 YAML로 PVC(Persistence Volume Claim)를 생성한다

```yaml
kubectl apply -f - <<EOF
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: azurefile
spec:
  accessModes:
  - ReadWriteMany
  storageClassName: azurefile
  resources:
    requests:
      storage: 1Gi
EOF
```

정상적으로 생성 확인

![PVC_1](https://github.com/user-attachments/assets/cd071285-a844-4930-a89d-5bf116f66618)

배포파일에 설정을 추가하고 배포한다

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: diagnosis
  labels:
    app: diagnosis
spec:
  replicas: 1
  selector:
    matchLabels:
      app: diagnosis
  template:
    metadata:
      labels:
        app: diagnosis
    spec:
      containers:
        - name: diagnosis
          image: "user04.azurecr.io/diagnosis:latest"
          ports:
             - containerPort: 8080  
          volumeMounts:
            - mountPath: "/mnt/data"
              name: volume
      volumes:
        - name: volume
          persistentVolumeClaim:
            claimName: azurefile                         
```

배포 후 컨테이너에 접속하여 제대로 파일시스템이 마운트되었는지 확인 후 test.txt파일을 생성한다

![PVC_2](https://github.com/user-attachments/assets/83c1699e-a6e3-4f4b-a4c6-521581d17192)

이후, 서비스를 2개로 Scale Out하고 확장된 주문 서비스에서도 test.txt가 확인되는지 검증한다

![PVC_3](https://github.com/user-attachments/assets/8c8166ae-d894-491b-a480-5e053bec83e6)

### 셀프 힐링/무정지배포 - Liveness/Rediness Probe

배포파일에 설정을 추가하고 배포한다

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: diagnosis
  labels:
    app: diagnosis
spec:
  replicas: 1
  selector:
    matchLabels:
      app: diagnosis
  template:
    metadata:
      labels:
        app: diagnosis
    spec:
      containers:
        - name: diagnosis
          image: "user04.azurecr.io/diagnosis:latest"
          ports:
             - containerPort: 8080
          readinessProbe:
            httpGet:
              path: "/diagnoses"
              port: 8080
            initialDelaySeconds: 10
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 10                    
```

siege 터미널을 열어서 충분한 시간만큼 부하를 주고 소스 수정해 재배포한다

```bash
kubectl exec -it siege -- /bin/bash
siege -c1 -t180S -v http://diagnosis:8080/diagnoses --delay=1S
exit
```

배포완료

![Rediness Probe_1](https://github.com/user-attachments/assets/55f885e0-04bd-4037-b4a8-5c0337f38c24)

![Rediness Probe_2](https://github.com/user-attachments/assets/02558869-4fbf-431d-b120-f2b42d853a99)

3분 동안 부하테스트 후 무정지 배포 완료

![Rediness Probe_3](https://github.com/user-attachments/assets/012ecc8c-b6de-4861-9952-37fb4d2cc1cd)

![Rediness Probe_4](https://github.com/user-attachments/assets/21243b4b-8724-4de2-8cef-8d7d7d2094e1)

### 서비스 메쉬 응용 - Mesh

Istio 설치

Istio add-on Dashboard 설치

Nginx Ingress Controller 설치 (Azure)

Istio Dashboard를 위한 라우팅 룰(Ingress) 설정

```yaml
kubectl apply -f - <<EOF
apiVersion: networking.k8s.io/v1
kind: "Ingress"
metadata: 
  name: "shopping-ingress"
  namespace: istio-system
  annotations: 
    nginx.ingress.kubernetes.io/ssl-redirect: "false"
    ingressclass.kubernetes.io/is-default-class: "true"
spec: 
  ingressClassName: nginx
  rules: 
    - host: ""
      http: 
        paths: 
          - path: /kiali
            pathType: Prefix
            backend: 
              service:
                name: kiali
                port:
                  number: 20001
          - path: /grafana
            pathType: Prefix
            backend: 
              service:
                name: grafana
                port:
                  number: 3000
          - path: /prometheus
            pathType: Prefix
            backend: 
              service:
                name: prometheus
                port:
                  number: 9090
          - path: /loki
            pathType: Prefix
            backend: 
              service:
                name: loki
                port:
                  number: 3100
EOF
```

설치 확인

![SVCMesh_7](https://github.com/user-attachments/assets/95991cc9-db95-45dd-b79e-d1ef0de41296)

Tracing Server - Jaeger  - ServiceType을 ClusterIP에서 LoadBalancer로 변경한다

```bash
kubectl patch svc tracing -n istio-system -p '{"spec": {"type": "LoadBalancer"}}'
```

새로운 namespace 생성 및 해당 namespace의 객체들에게 sidecar를 injection을 위해 istio-injection 활성화한다

```bash
kubectl create namespace medical
kubectl label namespace medical istio-injection=enabled
```

![SVCMesh_1](https://github.com/user-attachments/assets/2f46d503-af8a-4abe-8f4e-ded519481f2e)

트래픽, 라우팅이나 정책등을 위해 Istio의 Ingress Gateway를 설정하여 배포 한다

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: istio-medical-gateway
spec:
  # The selector matches the ingress gateway pod labels.
  # If you installed Istio using Helm following the standard documentation, this would be "istio=ingress"
  selector:
    istio: ingressgateway # use istio default controller
  servers:
  - port:
      number: 8080
      name: http
      protocol: HTTP
    hosts:
    - "*"
---
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: medicalinfo
spec:
  hosts:
  - "*"
  gateways:
  - istio-medical-gateway
  http:
  - match:
    - uri:
        prefix: /diagnoses
    route:
    - destination:
        host: diagnosis
        port:
          number: 8080
```

istio gateway에 연결된 서비스를 배포 및 EXTERNAL-IP를 확인한다

![SVCMesh_5](https://github.com/user-attachments/assets/12c60163-0b39-4fd8-9ff6-6bf5eda30911)

해당 주소로 동작을 확인한다&#x20;

![SVCMesh_3](https://github.com/user-attachments/assets/d05c843a-25cd-4ef9-91b0-774212bb9b08)

seige 명령으로 서비스에 부하를 발생한다

```bash
kubectl exec -it siege -- /bin/bash
siege -c20 -t60S -v 20.249.203.209/diagnoses
exit
```

![SVCMesh_8](https://github.com/user-attachments/assets/75f684d2-4fda-4b1d-bd28-e082b552e4cf)

Kiali를 통한 서비스 메쉬 모니터링

![SVCMesh_4](https://github.com/user-attachments/assets/7530120a-8e94-46e9-a724-0386b44975af)

Jaeger를 통한 서비스 트레이싱

![SVCMesh_6](https://github.com/user-attachments/assets/bf72350a-3dd6-44cf-9d7b-5c1f36ac6a9a)

### 통합 모니터링 - Loggregation/Monitoring

#### Prometheus

Prometheus UI 사용을 위해 Service Scope을 LoadBalancer Type으로 수정한다

```bash
kubectl patch service/prometheus -n istio-system -p '{"spec": {"type": "LoadBalancer"}}'
```

![로그_모니터링_1](https://github.com/user-attachments/assets/8c4721d4-d7ab-40a8-a4d2-1fb2c3feac79)

seige 명령으로 서비스에 부하를 발생한다

```bash
kubectl exec -it siege -- /bin/bash
siege -c20 -t60S -v 20.249.203.209/diagnoses
exit
```

![로그_모니터링_5](https://github.com/user-attachments/assets/9a58d4c2-9fde-490a-bfb0-6f7f1b3ad13c)

Prometheus service EXTERNAL-IP:9090에 접속하고 아래와 같이 Expression 브라우저 화면에서 조회한다

```
istio_requests_total{destination_service_name="diagnosis",app="diagnosis"}
istio_requests_total{app="diagnosis", destination_service="diagnosis.medical.svc.cluster.local", response_code = "200"}
```

![로그_모니터링_2](https://github.com/user-attachments/assets/7dcb1b03-bd12-4587-9d9d-e223b2c76f37)

![로그_모니터링_3](https://github.com/user-attachments/assets/3fbd3059-5988-4df0-9f33-044b7039c0c6)

PromQL기반의 그래프로 조회한다

```
rate(istio_requests_total{app="diagnosis",destination_service="diagnosis.medical.svc.cluster.local",response_code="200"}[5m])
```

![로그_모니터링_4](https://github.com/user-attachments/assets/e40d5ba4-c861-4f9c-8ce5-25fd1efec3c9)

#### Grafana

Grafana 사용을 위해 Service Scope을 LoadBalancer Type으로 수정한다

```bash
kubectl patch service/grafana -n istio-system -p '{"spec": {"type": "LoadBalancer"}}'
```

![로그_모니터링_6](https://github.com/user-attachments/assets/81f3248d-967d-4374-a604-4f752dfd3bdc)

seige 명령으로 서비스에 부하를 발생한다

```bash
kubectl exec -it siege -- /bin/bash
siege -c20 -t60S -v 20.249.203.209/diagnoses
exit
```

![로그_모니터링_8](https://github.com/user-attachments/assets/36f70223-b6eb-483d-99a6-af8962d0bfbb)

부하량에 따른 서비스 차트의 실시간 Gauge를 확인한다

아래와 같이 Network IO, CPU, Memory 사용량이 실시간 증가한다

![로그_모니터링_7](https://github.com/user-attachments/assets/6887c591-107c-4afb-b5c0-c7fe011a322c)
