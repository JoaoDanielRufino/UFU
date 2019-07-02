import React from 'react';
import * as cocoSsd from "@tensorflow-models/coco-ssd";
import "@tensorflow/tfjs";
import Webcam from 'react-webcam';
import { Container, Row, Col, Button, Dropdown, DropdownButton } from 'react-bootstrap';
import MyNavbar from './components/Navbar';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { loading: true, dropdownTitle: "Choose a Language", screenshot: null, result: null, confidence: 0 };
    this.model = null;
  }

  async componentDidMount() {
    this.model = await cocoSsd.load();
    this.setState({ loading: false });
  }

  setRef = webcam => {
    this.webcam = webcam;
  }

  capture = () => {
    const screenshot = this.webcam.getScreenshot();
    this.setState({ screenshot, result: null, confidence: 0 });
  }

  classify = async () => {
    const img = document.getElementById("image");
    const predictions = await this.model.detect(img);
    console.log(predictions);

    if (predictions.length === 0) {
      alert("0 predictions, try again");
    }
    else {
      const result = predictions[0].class; // Best prediction
      this.setState({ confidence: Math.round(predictions[0].score * 100) });
      fetch('http://localhost:8080/translate?text=' + result + '&language=' + this.state.dropdownTitle)
        .then(response => response.json())
        .then(response => {
          this.setState({ result: response.translation });
        });
    }
  }

  render() {
    return (
      <div>
        <MyNavbar />
        <Container>
          <Row>
            <Col>
              <Webcam audio={false} ref={this.setRef} style={{ height: 480, width: 480 }} screenshotFormat="image/jpeg" />
              <Row>
                <Col>
                  <Dropdown variant="info" >
                    <DropdownButton title={this.state.dropdownTitle} >
                      <Dropdown.Item onClick={() => this.setState({ dropdownTitle: "English" })} >English</Dropdown.Item>
                      <Dropdown.Item onClick={() => this.setState({ dropdownTitle: "Portuguese" })} >Portuguese</Dropdown.Item>
                      <Dropdown.Item onClick={() => this.setState({ dropdownTitle: "Spanish" })} >Spanish</Dropdown.Item>
                      <Dropdown.Item onClick={() => this.setState({ dropdownTitle: "French" })} >French</Dropdown.Item>
                      <Dropdown.Item onClick={() => this.setState({ dropdownTitle: "Italian" })} >Italian</Dropdown.Item>
                      <Dropdown.Item onClick={() => this.setState({ dropdownTitle: "German" })} >German</Dropdown.Item>
                    </DropdownButton>
                  </Dropdown>
                </Col>
                <Col>
                  {this.state.loading ? "Loading model..." : <Button variant="primary" onClick={this.capture} >Capture photo</Button> }
                </Col>
              </Row>
            </Col>

            <Col>
              <Row>
                {this.state.screenshot ? <img id="image" style={{ marginTop: 60 }} src={this.state.screenshot} alt="Nothing" onLoad={this.classify} /> : null}
              </Row>
              <Row>
                <Col>
                  <br />
                  {this.state.result ? <span>Prediction: {this.state.result}</span> : null} 
                </Col>
                <Col>
                  <br />
                  {this.state.result ? <span>Confidence: {this.state.confidence}%</span> : null} 
                </Col>
              </Row>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default App;
