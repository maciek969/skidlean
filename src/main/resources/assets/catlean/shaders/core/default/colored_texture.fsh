#version 330 core

out vec4 color;

uniform sampler2D Sampler0;

in vec2 texCoord0;
in vec4 vertexColor;

void main() {
    color = texture(Sampler0, texCoord0) * vertexColor;
}
