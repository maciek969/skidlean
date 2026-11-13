#version 330 core

uniform sampler2D Sampler0;

layout(std140) uniform UBO {
    vec2 uHalfTexelSize;
};

in vec2 uv;
out vec4 color;

void main() {
    vec3 col = vec3(0.0);
    float totalAlpha = 0.0;

    for (int x = -1; x <= 1; x++) {
        for (int y = -1; y <= 1; y++) {
            if (totalAlpha < 1.0 && (x != y || x == 0 && y == 0)) {
                vec2 offset = vec2(float(x), float(y)) * uHalfTexelSize * 0.5;
                vec4 sample = texture(Sampler0, uv + offset);
                col += sample.rgb;
                totalAlpha += sample.a;
            }
        }
    }

    if (totalAlpha > 0.0) {
        color = vec4(col, totalAlpha);
    } else {
        discard;
    }
}